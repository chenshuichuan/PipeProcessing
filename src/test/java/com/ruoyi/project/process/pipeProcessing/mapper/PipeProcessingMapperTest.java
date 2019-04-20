package com.ruoyi.project.process.pipeProcessing.mapper;

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
 * Date: 2019/4/19
 * Time: 22:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipeProcessingMapperTest {

    @Autowired
    private PipeProcessingMapper pipeProcessingMapper;

    @Test
    public void selectByArrangeId() {

        Integer arrangeId = 27;
        List<Integer> pipeIds = pipeProcessingMapper.selectByArrangeId(arrangeId);
        Assert.assertThat(pipeIds,notNullValue());
        Assert.assertThat(pipeIds.size(),greaterThan(0));
        System.out.println("find size = "+pipeIds.size());
    }
}