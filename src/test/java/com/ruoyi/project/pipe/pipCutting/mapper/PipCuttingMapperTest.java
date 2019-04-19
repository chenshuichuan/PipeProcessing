package com.ruoyi.project.pipe.pipCutting.mapper;

import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/19
 * Time: 11:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipCuttingMapperTest {
    @Autowired
    private PipCuttingMapper pipCuttingMapper;
    @Test
    public void findBySuffix() {

    }
    @Test
    public void selectPipCuttingByIds() {
        List<Integer> integerList = new ArrayList<>();
        for (int i= 0; i<10000;i++){
            integerList.add(i);
        }
        List<PipCutting> batchList = pipCuttingMapper.selectPipCuttingByIds(integerList);
        Assert.assertThat(batchList,notNullValue());
        Assert.assertThat(batchList.size(),greaterThan(0));
        System.out.println("find size = "+batchList.size());
    }
}