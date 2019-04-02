package com.ruoyi.project.pipe.unit.service;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/2
 * Time: 21:46
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitServiceImplTest {
    @Autowired
    private  IUnitService unitService;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Autowired
    private IProcessPlanService processPlanService;

    @Test
    public void analysisOrder() {
    }

    @Test
    public void analysisOrderByProcessPlan() {
        int id=773;
        ProcessPlan processPlan = processPlanService.selectProcessPlanById(id);

        unitService.analysisOrderByProcessPlan(processPlan);
        System.out.println("finished");
    }
}