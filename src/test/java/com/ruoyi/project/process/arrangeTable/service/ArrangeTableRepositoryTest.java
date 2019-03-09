package com.ruoyi.project.process.arrangeTable.service;

import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.unit.service.UnitRepository;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import com.ruoyi.project.process.batchProcessing.service.BatchProcessingRepository;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.OrderRepository;
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import com.ruoyi.project.process.pipeProcessing.service.PipeProcessingRepository;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.TaoliaoRepository;
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import com.ruoyi.project.process.unitProcessing.service.UnitProcessingRepository;
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
 * Date: 2019/3/8
 * Time: 17:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArrangeTableRepositoryTest {

    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private BatchProcessingRepository batchProcessingRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PipeProcessingRepository pipeProcessingRepository;
    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private UnitProcessingRepository unitProcessingRepository;

    @Test
    public void arrangeTableRepository() {
        List<ArrangeTable> objects = arrangeTableRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }
    @Test
    public void batchProcessingRepository() {
        List<BatchProcessing> objects = batchProcessingRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }
    @Test
    public void orderRepository() {
        List<Order> objects = orderRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }
    @Test
    public void pipeProcessingRepository() {
        List<PipeProcessing> objects = pipeProcessingRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }
    @Test
    public void taoliaoRepository() {
        List<Taoliao> objects = taoliaoRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }
    @Test
    public void unitProcessingRepository() {
        List<UnitProcessing> objects = unitProcessingRepository.findAll();
        Assert.assertThat(objects,notNullValue());
        Assert.assertThat(objects.size(),greaterThan(0));
        System.out.println("find size = "+objects.size());
    }


}