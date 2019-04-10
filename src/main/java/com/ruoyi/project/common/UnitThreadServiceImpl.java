package com.ruoyi.project.common;/**
 * Created by:Ricardo
 * Description:
 * Date: 2018/6/21
 * Time: 22:08
 */

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.process.order.service.OrderRepository;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
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
public class UnitThreadServiceImpl implements UnitThreadService {


    private final static Logger logger = LoggerFactory.getLogger(UnitThreadServiceImpl.class);


    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private PipeRepository pipeRepository;

    @Autowired
    private IPipeProcessingService pipeProcessingService;

    @Async
    @Override
    public void unitArrange(Unit unit, ArrangeTable arrangeTable, ProcessStage nextSatge) {
        List<Pipe> pipeList = pipeRepository.findByBatchIdAndUnitId(unit.getBatchId(),unit.getId());
        for (Pipe pipe: pipeList){
            pipeProcessingService.arrangePipe(pipe,arrangeTable,unit,nextSatge);
        }
    }
}
