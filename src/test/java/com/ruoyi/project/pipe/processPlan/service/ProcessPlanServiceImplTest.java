package com.ruoyi.project.pipe.processPlan.service;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.mapper.ProcessPlanMapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/1
 * Time: 22:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPlanServiceImplTest {
    @Autowired
    private IProcessPlanService processPlanService;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Autowired
    private ProcessPlanMapper processPlanMapper;

    @Test
    //@Transactional
    public void judgeBatchUnitOfPlan() {
        int planId = 772;
        ProcessPlan processPlan = processPlanMapper.selectProcessPlanById(planId);
        processPlanService.judgeBatchUnitOfPlan(processPlan);

        processPlan = processPlanMapper.selectProcessPlanById(planId);
        Assert.assertThat(processPlan,notNullValue());
        Assert.assertThat(processPlan.getAnalysisStatus(),equalTo(1));
        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入你的姓名：");
//        String name = sc.nextLine();
//        System.out.println("you name is :"+name);
    }

    @Test
    public void judgeBatchUnitByPlanName() {
        String planName = "极地船加工计划-测试1";
        processPlanService.judgeBatchUnitByPlanName(planName);


    }
}