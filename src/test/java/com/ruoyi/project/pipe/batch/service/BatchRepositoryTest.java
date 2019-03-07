package com.ruoyi.project.pipe.batch.service;

import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.system.files.domain.Files;
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
 * @author Ricardo
 * Description:
 * Date: 2019/3/7
 * Time: 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchRepositoryTest {

    @Autowired
    private BatchRepository batchRepository;
    @Test
    public void findBySuffix() {
        List<Batch> batchList = batchRepository.findAll();
        Assert.assertThat(batchList,notNullValue());
        Assert.assertThat(batchList.size(),greaterThan(0));
        System.out.println("find size = "+batchList.size());
    }

}