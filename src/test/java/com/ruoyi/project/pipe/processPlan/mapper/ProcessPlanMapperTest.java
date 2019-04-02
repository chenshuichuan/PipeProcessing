package com.ruoyi.project.pipe.processPlan.mapper;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/31
 * Time: 12:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessPlanMapperTest {
    @Autowired
    private ProcessPlanMapper planMapper;

    @Autowired
    private ProcessPlanRepository processPlanRepository;

    @Test
    public void selectPlanNameList() {
        List<String> shipSimpleList = planMapper.selectPlanNameList(null);
        Assert.assertThat(shipSimpleList,notNullValue());
        Assert.assertThat(shipSimpleList.size(),greaterThan(0));
    }

    @Test
    public void selectListByPlanName() {
        ProcessPlan processPlan = new ProcessPlan();
        processPlan.setPlanName("极地船加工计划-测试1");
        List<ProcessPlan> processPlanList = planMapper.selectProcessPlanList(processPlan);
        Assert.assertThat(processPlanList,notNullValue());
        Assert.assertThat(processPlanList.size(),greaterThan(0));
        System.out.println("size = "+processPlanList.size());
    }
    @Test
    public void findByPlanName() {
        List<ProcessPlan> processPlanList = processPlanRepository.findByPlanName("极地船加工计划-测试1");
        Assert.assertThat(processPlanList,notNullValue());
        Assert.assertThat(processPlanList.size(),greaterThan(0));
        System.out.println("size = "+processPlanList.size());
    }
}

