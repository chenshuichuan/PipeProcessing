package com.ruoyi.project.process.order.service;

import java.util.List;

import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.order.mapper.OrderMapper;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.common.support.Convert;

/**
 * 加工顺序 服务层实现
 *
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private static final Logger logger=LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询加工顺序信息
     *
     * @param id 加工顺序ID
     * @return 加工顺序信息
     */
    @Override
    public Order selectOrderById(Integer id) {
        return orderMapper.selectOrderById(id);
    }

    /**
     * 查询加工顺序列表
     *
     * @param order 加工顺序信息
     * @return 加工顺序集合
     */
    @Override
    public List<Order> selectOrderList(Order order) {
        return orderMapper.selectOrderList(order);
    }

    /**
     * 新增加工顺序
     *
     * @param order 加工顺序信息
     * @return 结果
     */
    @Override
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    /**
     * 修改加工顺序
     *
     * @param order 加工顺序信息
     * @return 结果
     */
    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    /**
     * 删除加工顺序对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOrderByIds(String ids) {
        return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
    }

    @Override
    public ProcessStage getNextProcessStage(Integer orderId, Integer currentStage) {

        if (currentStage == null ||orderId == null){
            return null;
        }
        Order order = orderMapper.selectOrderById(orderId);
        if (order == null|| order.getOrderList() == null){
            logger.error("数据错误！，请检查数据！");
            return null;
        }
        ProcessStage processStage = new ProcessStage();
        processStage.setOrderId(order.getId());

        String[] strings = order.getOrderList().split(",");
        int i=0;
        for (; i<strings.length; i++){
            if(Integer.toString(currentStage).equals(strings[i])){
                break;
            }
        }
        //当前已经是最后一个工序了
        if(i==strings.length-1){
            DictData dictData = getPipeProcessType("已完成");
            int finished = Integer.parseInt(dictData.getDictValue());
            processStage.setStage(finished);
            processStage.setStageIndexOfOrder(strings.length+1);
            processStage.setStageName(dictData.getDictLabel());
            return processStage;

        }
        else{
            Integer stageId = Integer.parseInt(strings[i+1]);
            DictData dictData = getPipeProcessType(stageId);
            processStage.setStage(stageId);
            processStage.setStageIndexOfOrder(i+1);
            processStage.setStageName(dictData.getDictLabel());
            return processStage;
        }

    }

    /**
     * 根据加工类型名称获取字典中的类型数据**/
    @Override
    public DictData getPipeProcessType(String type){
        DictData dictData = new DictData();
        dictData.setDictType("pipe_process_type");
        dictData.setDictLabel(type);
        List<DictData> dictList = dictDataService.selectDictDataList(dictData);
        if(dictList == null || dictList.size()!=1){
            logger.error("数据字典错误！");
            return null;
        }
        else {
            return dictList.get(0);
        }
    }

    @Override
    public DictData getPipeProcessType(Integer processOrder) {
        DictData dictData = new DictData();
        dictData.setDictType("pipe_process_type");
        dictData.setDictValue(Integer.toString(processOrder));
        List<DictData> dictList = dictDataService.selectDictDataList(dictData);
        if(dictList == null || dictList.size()!=1){
            logger.error("数据字典错误！");
            return null;
        }
        else {
            return dictList.get(0);
        }
    }

    @Override
    public DictData getWorkPlaceStatus(String type) {
        DictData dictData = new DictData();
        dictData.setDictType("workplace_status");
        dictData.setDictLabel(type);
        List<DictData> dictList = dictDataService.selectDictDataList(dictData);
        if(dictList == null || dictList.size()!=1){
            logger.error("数据字典错误！");
            return null;
        }
        else {
            return dictList.get(0);
        }
    }

    @Override
    public DictData getWorkPlaceStatus(Integer processOrder) {
        DictData dictData = new DictData();
        dictData.setDictType("workplace_status");
        dictData.setDictValue(Integer.toString(processOrder));
        List<DictData> dictList = dictDataService.selectDictDataList(dictData);
        if(dictList == null || dictList.size()!=1){
            logger.error("数据字典错误！");
            return null;
        }
        else {
            return dictList.get(0);
        }
    }

}
