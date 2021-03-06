package com.ruoyi.project.common;/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/6/21
 * Time: 22:08
 */

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.pipCutting.service.IPipCuttingService;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.pipe.unit.service.UnitRepository;
import com.ruoyi.project.pipe.workPipe.service.WorkPipeRepository;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.service.ArrangeTableRepository;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.process.order.service.OrderRepository;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.project.process.taoliaoOnline.service.ITaoliaoOnlineService;
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.mapper.WorkplaceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Future;

/**
 *@ClassName: GenerateExcelThreadServiceImpl
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2018/6/21 22:08
 **/
@Service
public class ProjectThreadServiceImpl implements ProjectThreadService {


    private final static Logger logger = LoggerFactory.getLogger(ProjectThreadServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private IShipService shipService;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Autowired
    private IUnitService unitService;
    @Autowired
    private IBatchArrangeService batchArrangeService;
    @Autowired
    private ICutPlanService cutPlanService;
    @Autowired
    private PipeRepository pipeRepository;
    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private WorkplaceMapper workplaceMapper;
    @Autowired
    private IPipeProcessingService pipeProcessingService;

    @Autowired
    private ITaoliaoOnlineService taoliaoOnlineService;
    /**
     * 异常调用返回Future
     *
     * @param i
     * @return
     * @throws InterruptedException
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
        System.out.println("input is " + i);
        Thread.sleep(1000 * 5);
        Future<String> future = new AsyncResult<String>("success:" + i);
        // Future接收返回值，这里是String类型，可以指明其他类型
        return future;
    }


    @Async
    @Override
    public void executeGenerateAward(String model,int year) {
        System.out.println("执行异步生成分数任务：executeGenerateAward");
        //计算创新平均分
        System.out.println("任务："+model+",year:"+year);
        System.out.println("excelService.finalScoreExcel 执行完成");
    }

    /**
     * 根据计划名称对计划下关联的单元进行解析-加工顺序
     *
     * **/
    @Async
    @Override
    public void judgeBatchUnitByPlanName(String planName) {
        if(planName.isEmpty()){
            return ;
        }
        logger.info("执行异步解析单元加工顺序任务-开始：judgeBatchUnitByPlanName("+planName+")");
        //计算创新平均分
        MiddleStatus middleStatus = new MiddleStatus("执行异步解析单元加工顺序任务",
                "pipe_process_plan",planName,"judgeBatchUnitByPlanName(String planName)");
        middleStatusRepository.save(middleStatus);

        List<ProcessPlan> processPlanList = processPlanRepository.findByPlanName(planName);
        for (ProcessPlan processPlan: processPlanList){
            unitService.analysisOrderByProcessPlan(processPlan);
        }
        logger.info("执行异步解析单元加工顺序任务-完成：judgeBatchUnitByPlanName("+planName+")");
    }
    @Async
    @Override
    public void cutArrange(ArrangeInfo arrangeInfo){
        logger.info("异步线程-cutArrange(ArrangeInfo arrangeInfo)");

        CutPlan cutPlan = cutPlanService.selectCutPlanById(arrangeInfo.getId());
        //更新该cutPlan 的呗下料状态
        cutPlan.setIsArrange(1);
        cutPlanService.updateCutPlan(cutPlan);

        //批次派工
        batchArrangeService.arrangeCutPlan(arrangeInfo,cutPlan);
        //批次派工时生成套料管材表记录
        //拿到派工单
        if(arrangeInfo.getOnebigCutId()!=null&&arrangeInfo.getOnebigCutNumber()>0){
            generateTaoliaoRecord(arrangeInfo, arrangeInfo.getOnebigCutId());
        }
        if(arrangeInfo.getOneCutId()!=null&&arrangeInfo.getOneTotalNumber()>0){
            generateTaoliaoRecord(arrangeInfo, arrangeInfo.getOneCutId());
        }
        if(arrangeInfo.getTwoCutId()!=null&&arrangeInfo.getTwoTotalNumber()>0){
            generateTaoliaoRecord(arrangeInfo, arrangeInfo.getTwoCutId());
        }
    }
    private boolean generateTaoliaoRecord(ArrangeInfo arrangeInfo, Integer cutPlaceId){
        //批次派工时生成套料管材表记录
        //拿到派工单
        Workplace workplace = workplaceMapper.selectWorkplaceById(cutPlaceId);
        ArrangeTable arrangeTable = arrangeTableRepository.findByTypeAndPlanIdAndStageAndWorkplace(
                1,arrangeInfo.getId(),"下料",workplace.getWorkplaceCode()
        );
        if(null ==  workplace||null == arrangeTable){
            MiddleStatus middleStatus = new MiddleStatus("批次派工时生成套料管材表记录出现错误",
                    "process_arrange_table",arrangeInfo.toString(),"生成套料管材表记录");
            middleStatusRepository.save(middleStatus);
        }
        else{
            taoliaoOnlineService.generateTaoliao(arrangeTable);
        }
        return true;
    }
}
