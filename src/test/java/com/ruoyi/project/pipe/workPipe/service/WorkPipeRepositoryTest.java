package com.ruoyi.project.pipe.workPipe.service;

import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.mapper.PipeMapper;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
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
 * Date: 2019/4/9
 * Time: 16:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkPipeRepositoryTest {

    @Autowired
    private WorkPipeRepository workPipeRepository;
    @Autowired
    private PipeMapper pipeMapper;

    @Test
    public void findByAssemblyPipeIdAndShapeShipId() {
        List<WorkPipe> workPipeList = workPipeRepository.findByAssemblyPipeIdAndShapeShipId(57,6);

        Assert.assertThat(workPipeList,notNullValue());
        Assert.assertThat(workPipeList.size(),greaterThan(0));


    }

    @Test
    public void findByAssemblyPipeIdAndShapeShipIdAndCutLength() {
        List<WorkPipe> workPipeList = workPipeRepository.findByAssemblyPipeIdAndShapeShipIdAndCutLength(87,6,164);

        Assert.assertThat(workPipeList,notNullValue());
        Assert.assertThat(workPipeList.size(),greaterThan(0));
    }
}