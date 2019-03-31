package com.ruoyi.project.pipe.processPlan.mapper;

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
    @Test
    public void selectPlanNameList() {
        List<String> shipSimpleList = planMapper.selectPlanNameList(null);
        Assert.assertThat(shipSimpleList,notNullValue());
        Assert.assertThat(shipSimpleList.size(),greaterThan(0));
    }
}

