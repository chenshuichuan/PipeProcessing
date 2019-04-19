package com.ruoyi.project.common;

import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.PipeRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.process.order.domain.ProcessStage;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@ClassName: GenerateExcelThreadServiceImpl
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2018/6/21 22:08
 **/
@Service
public class BatchThreadServiceImpl implements BatchThreadService {


    private final static Logger logger = LoggerFactory.getLogger(BatchThreadServiceImpl.class);


    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private PipeRepository pipeRepository;

    @Autowired
    private IBatchArrangeService batchArrangeService;
//    @Async
//    @Override
//    public void generateTaoliaoRecord(PlanTable planTable) {
//
//    }
}
