package com.ruoyi.project.pipe.unit.service;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;
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
    @Autowired
    private UnitRepository unitRepository;
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
    @Test
    public void findByShicodeAndBatchName() {
        //极地重载甲板运输船1#
        String shipCode = "14664001";
        String batchName = "G03";
        List<Unit> unitList = unitRepository.findByShipCodeAndBatchName(shipCode,batchName);
        Assert.assertThat(unitList,notNullValue());
        Assert.assertThat(unitList.size(),greaterThan(0));
        System.out.println("find size = "+unitList.size());
    }
}