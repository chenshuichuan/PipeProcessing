package com.ruoyi.project.process.unitArrange.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.IPipeService;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.project.process.order.service.OrderRepository;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import com.ruoyi.project.process.unitProcessing.service.IUnitProcessingService;
import com.ruoyi.project.process.unitProcessing.service.UnitProcessingRepository;
import com.ruoyi.project.system.dict.domain.DictData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;

/**
 * 单元派工 服务层实现
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Service
public class UnitArrangeServiceImpl implements IUnitArrangeService 
{
    private static final Logger logger=LoggerFactory.getLogger(UnitArrangeServiceImpl.class);
    @Autowired
    private IUnitProcessingService unitProcessingService;
    @Autowired
    private IPipeService pipeService;
    @Autowired
    private PipeRepository pipeRepository;
    @Autowired
    private UnitProcessingRepository unitProcessingRepository;
    @Autowired
    private IPipeProcessingService pipeProcessingService;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private ICutPlanService cutPlanService;
    @Override
    public int arrangeUnit(Unit unit, ArrangeTable arrangeTable) {
        if(unit==null || arrangeTable ==null){
            return 0;
        }
        int currentSatgeId = unit.getNextStageId();
        ProcessStage nextSatge = orderService.getNextProcessStage(unit.getProcessOrderId(),currentSatgeId);

        //构造单元加工信息
        UnitProcessing unitProcessing = new UnitProcessing(unit.getId(),unit.getBatchId(),
                nextSatge.getStage(),nextSatge.getStageIndexOfOrder(),
                unit.getPipeNumber(),0, arrangeTable.getId(),new Date(),0);
        unitProcessingService.insertUnitProcessing(unitProcessing);


        //更新unit数据
        unit.setProcessStageId(currentSatgeId);
        unit.setNextStageId(nextSatge.getStage());
        unit.setUpdateTime(new Date());
        unitService.updateUnit(unit);

        List<Pipe> pipeList = pipeRepository.findByBatchIdAndUnitId(unit.getBatchId(),unit.getId());
        for (Pipe pipe: pipeList){
            pipeProcessingService.arrangePipe(pipe,arrangeTable,unit,nextSatge);
        }

        return 1;
    }

    /**
     * 单元的下料工段只有一个，或是特殊管E开头的单元//特殊管直接扔去二部下料工段
     * */
    @Override
    public int arrangeUnitCut(Unit unit, ArrangeTable arrangeTable) {
        if(unit==null || arrangeTable ==null){

            return 0;
        }
        DictData dictData = orderService.getPipeProcessType("下料");
        int currentSatgeId = Integer.parseInt(dictData.getDictValue());

        //构造单元加工信息
        UnitProcessing unitProcessing = new UnitProcessing(unit.getId(),unit.getBatchId(),
                currentSatgeId,1,
                unit.getPipeNumber(),0, arrangeTable.getId(),new Date(),0);
        unitProcessingService.insertUnitProcessing(unitProcessing);

        //更新unit数据
        unit.setProcessStageId(currentSatgeId);
        ProcessStage nextSatge = orderService.getNextProcessStage(unit.getProcessOrderId(),currentSatgeId);
        unit.setNextStageId(nextSatge.getStage());
        unit.setUnprocessNumber(0);
        unit.setProcessingNumber(unit.getPipeNumber());
        unit.setProcessedNumber(0);
        unit.setUpdateTime(new Date());
        unitService.updateUnit(unit);

        //派工推送到管件一级
        List<Pipe> pipeList = pipeRepository.findByBatchIdAndUnitId(unit.getBatchId(),unit.getId());
        for (Pipe pipe: pipeList){
            //产生PipeProcessing记录
            //更新Pipe当前状态
            pipeProcessingService.arrangePipe(pipe,arrangeTable,unit,nextSatge);

        }

        return 1;
    }
    /**
     * //下料派工看下料计划，大岗下料、一部下料、二部下料
     * //钢管 外径$140以上 在大岗下料，$114 以下在一部，特殊管在 二部（$22~60: 小管，$76~168:中管，$219+:大管）所以中管有的会在一部下料，有的在大岗下料
     * */
    @Override
    public int arrangeUnitCut(Unit unit, ArrangeTable onebigSection, ArrangeTable oneSection, ArrangeTable twoSection) {
        if(unit==null){
            return 0;
        }
        DictData dictData = orderService.getPipeProcessType("下料");
        int currentSatgeId = Integer.parseInt(dictData.getDictValue());

        //更新unit数据
        unit.setProcessStageId(currentSatgeId);
        ProcessStage nextSatge = orderService.getNextProcessStage(unit.getProcessOrderId(),currentSatgeId);
        unit.setNextStageId(nextSatge.getStage());
        unit.setUnprocessNumber(0);
        unit.setProcessingNumber(unit.getPipeNumber());
        unit.setProcessedNumber(0);
        unit.setUpdateTime(new Date());
        unitService.updateUnit(unit);

        int onebigCutnumber = 0;
        int oneCutNumber = 0;
        int twoCutNumber = 0;
        Ship ship = shipRepository.findByShipCode(unit.getShipCode());
        List<Pipe> pipeList = pipeRepository.findByBatchIdAndUnitId(unit.getBatchId(),unit.getId());

        for (Pipe pipe: pipeList){
            //取得管的管子外径
            WorkPipe workPipe = pipeService.getWorkPipeBy(pipe,ship);
            //判断属于哪个派工单
            if(workPipe == null){
                MiddleStatus middleStatus = new MiddleStatus("管件未能找到对应的WorkPipe信息","pipe_work_pipe",pipe.toString(),"arrangeUnitCut");
                middleStatusRepository.save(middleStatus);
            }
            //大岗下料
            else if(workPipe.getPipeOutDiameter()>140){
                //产生PipeProcessing记录
                //更新Pipe当前状态
                pipeProcessingService.arrangePipe(pipe,onebigSection,unit,nextSatge);
                onebigCutnumber++;
            }
            else if(workPipe.getPipeOutDiameter()<114){
                //产生PipeProcessing记录
                //更新Pipe当前状态
                pipeProcessingService.arrangePipe(pipe,oneSection,unit,nextSatge);
                oneCutNumber++;
            }
        }

        //构造单元加工信息
        if(onebigSection!=null){
            UnitProcessing unitProcessing = new UnitProcessing(unit.getId(),unit.getBatchId(),
                    currentSatgeId,1,
                    onebigCutnumber,0, onebigSection.getId(),new Date(),0);
            unitProcessingService.insertUnitProcessing(unitProcessing);
        }
        if(oneSection!=null){
            UnitProcessing unitProcessing = new UnitProcessing(unit.getId(),unit.getBatchId(),
                    currentSatgeId,1,
                    oneCutNumber,0, oneSection.getId(),new Date(),0);
            unitProcessingService.insertUnitProcessing(unitProcessing);
        }
        //二部好像不会来到这里？？？？？注意
        if(twoSection!=null){
            UnitProcessing unitProcessing = new UnitProcessing(unit.getId(),unit.getBatchId(),
                    currentSatgeId,1,
                    unit.getPipeNumber(),0, twoSection.getId(),new Date(),0);
            unitProcessingService.insertUnitProcessing(unitProcessing);
        }

        return 1;
    }
}
