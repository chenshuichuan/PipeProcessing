package com.ruoyi.project.pipe.unit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipCutting.service.IPipCuttingService;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.UnitSimple;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.pipe.workPipe.service.WorkPipeRepository;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.unit.mapper.UnitMapper;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.common.support.Convert;

/**
 * 加工单元 服务层实现
 *
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class UnitServiceImpl implements IUnitService {
    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PipeRepository pipeRepository;
    @Autowired
    private WorkPipeRepository workPipeRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private IShipService shipService;
    @Autowired
    private IPipCuttingService pipCuttingService;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;

    /**
     * 查询加工单元信息
     *
     * @param id 加工单元ID
     * @return 加工单元信息
     */
    @Override
    public Unit selectUnitById(Integer id) {
        return unitMapper.selectUnitById(id);
    }

    /**
     * 查询加工单元列表
     *
     * @param unit 加工单元信息
     * @return 加工单元集合
     */
    @Override
    public List<Unit> selectUnitList(Unit unit) {
        return unitMapper.selectUnitList(unit);
    }

    /**
     * 新增加工单元
     *
     * @param unit 加工单元信息
     * @return 结果
     */
    @Override
    public int insertUnit(Unit unit) {
        return unitMapper.insertUnit(unit);
    }

    /**
     * 修改加工单元
     *
     * @param unit 加工单元信息
     * @return 结果
     */
    @Override
    public int updateUnit(Unit unit) {
        return unitMapper.updateUnit(unit);
    }

    /**
     * 删除加工单元对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUnitByIds(String ids) {
        return unitMapper.deleteUnitByIds(Convert.toStrArray(ids));
    }

    /**
     * 加工顺序规则解析！！！非常重要
     * 解析单元下所有管件加工顺序
     * 1.拿到所有管件对管件进行循环
     * 2.拿到管件对应的pipeCutting 和 workPipe数据
     * 3.对管件对应的加工顺序进行判断
     * @param unit 要解析顺序的单元
     *             注意，得到的加工顺序并不保存为unit的加工顺序
     */
    @Override
    public Order analysisOrder(Unit unit) {
        if(unit == null){
            return null;
        }
        Map<Integer, Integer> orderIntegerMap = new HashMap<Integer, Integer>();
        List<Order> orderList = orderRepository.findAll();
        for (Order order: orderList){
            orderIntegerMap.put(order.getId(),0);
        }

        List<Pipe> pipeList = pipeRepository.findByBatchIdAndUnitId(unit.getBatchId(),unit.getId());
        Ship ship = shipRepository.findByShipCode(unit.getShipCode());

        for(Pipe pipe: pipeList){
            //因为中间程序设定pipe_pipe的id和pipe_pip_cutting  的id一致的
            PipCutting pipCutting = pipCuttingService.selectPipCuttingById(pipe.getId());
            List<WorkPipe> workPipeList = workPipeRepository.findByAssemblyPipeIdAndShapeShipId(pipe.getAssemblyPipeId(),Integer.parseInt(ship.getShapeShipId()));

            //不确定查找出来的workPipe是否唯一
            //就是不唯一的。。。
            if(workPipeList.size()>=1){
                if(workPipeList.size()>1){
                    MiddleStatus middleStatus = new MiddleStatus("预期数据有问题，得到workPipe大于1","pipe_worke_pipe",pipe.toString(),"analysisOrder(Unit unit)");
                    middleStatusRepository.save(middleStatus);
                }
                Order order = queryOrder(unit,pipCutting, workPipeList.get(0));
                int number = orderIntegerMap.get(order.getId());
                number++;
                orderIntegerMap.put(order.getId(),number);
                //设置
                pipe.setProcessOrder(order.getId());
                pipeRepository.save(pipe);
            }
            else{
                MiddleStatus middleStatus = new MiddleStatus("预期数据有问题，得到workPipe小于1","pipe_worke_pipe",pipe.toString(),"analysisOrder(Unit unit)");
                middleStatusRepository.save(middleStatus);
            }
        }

        //遍历map 的key
        Integer maxOrder=0;
        for (Integer key : orderIntegerMap.keySet()) {
            if(maxOrder==0){
                maxOrder = key;
            }
            else if(orderIntegerMap.get(key)>orderIntegerMap.get(maxOrder)){
                maxOrder = key;
            }
        }
        //再回数据库查找，找到返回加工顺序
        Optional<Order> optionalOrder = orderRepository.findById(maxOrder);
        return optionalOrder.orElse(null);
    }
    /**
     * 涉及什么数据，就在这个函数添加规则
     * */
    private Order queryOrder(Unit unit,PipCutting pipCutting, WorkPipe workPipe){
        int unitHasF = 0;
        int pipeShapeHasBend = 0;
        int hasSurfaceTreat = 0;
        ///这里注意 要修改的
        if(unit.getName().contains("F")){
           unitHasF = 1;
        }
        if (pipCutting.getPipeShape().contains("弯")){
            pipeShapeHasBend = 1;
        }
        if(! workPipe.getSurfaceTreat().isEmpty()){
            hasSurfaceTreat = 1;
        }
        Order order = orderRepository.findByUnitHasFAndPipeShapeHasBendAndHasSurfaceTreat(unitHasF,
                pipeShapeHasBend, hasSurfaceTreat);
        return order;
    }


    /**
     * 计算某process下包含的单元的加工顺序，并保存
     * 这个函数需要在已经解析完单元所属计划的步骤之后调用才能成功
     * */
    @Override
    public int analysisOrderByProcessPlan(ProcessPlan processPlan){
        List<Unit> unitList = unitRepository.findByPlanId(processPlan.getId());
        for(Unit unit: unitList){
            Order order = analysisOrder(unit);
            if(order==null){
                MiddleStatus middleStatus = new MiddleStatus("预期数据有问题",
                        "pipe_process_plan",processPlan.toString(),"analysisOrder(ProcessPlan processPlan)");
                middleStatusRepository.save(middleStatus);
            }
            else {
                unit.setProcessOrderId(order.getId());
                //其他的“当前工序”、下一工序“ 到派工的时候再进行初始化
                unitRepository.save(unit);
            }
        }
        return 0;
    }

    @Override
    public List<Unit> selectByShipNameAndBatchName(String shipName, String batchName) {
        Ship ship = shipRepository.findByShipName(shipName);
        if(ship ==null){
            MiddleStatus middleStatus = new MiddleStatus("selectByShipNameAndBatchName error! ",
                    "pipe_unit",shipName,"findByShipName(shipName)");
            middleStatusRepository.save(middleStatus);
            return null;
        }
        return unitRepository.findByShipCodeAndBatchName(ship.getShipCode(),batchName);
    }
    @Override
    public List<UnitSimple> selectUnitSimpleByShipNameAndBatchName(String shipName, String batchName) {
        Ship ship = shipRepository.findByShipName(shipName);
        if(ship ==null){
            MiddleStatus middleStatus = new MiddleStatus("selectUnitSimpleByShipNameAndBatchName error! ",
                    "pipe_unit",shipName,"findByShipName(shipName)");
            middleStatusRepository.save(middleStatus);
            return null;
        }
        UnitSimple unitSimple = new UnitSimple();
        unitSimple.setShipCode(ship.getShipCode());
        unitSimple.setBatchName(batchName);
        return unitMapper.selectUnitSimpleList(unitSimple);
    }
}
