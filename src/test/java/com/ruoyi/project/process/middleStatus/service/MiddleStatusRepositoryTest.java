package com.ruoyi.project.process.middleStatus.service;

import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.mapper.ArrangeTableMapper;
import com.ruoyi.project.process.arrangeTable.service.ArrangeTableRepository;
import com.ruoyi.project.process.arrangeTable.service.IArrangeTableService;
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import com.ruoyi.project.process.batchProcessing.mapper.BatchProcessingMapper;
import com.ruoyi.project.process.batchProcessing.service.BatchProcessingRepository;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.mapper.MiddleStatusMapper;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.mapper.OrderMapper;
import com.ruoyi.project.process.order.service.OrderRepository;
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import com.ruoyi.project.process.pipeProcessing.mapper.PipeProcessingMapper;
import com.ruoyi.project.process.pipeProcessing.service.PipeProcessingRepository;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.mapper.TaoliaoMapper;
import com.ruoyi.project.process.taoliao.service.TaoliaoRepository;
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import com.ruoyi.project.process.unitProcessing.mapper.UnitProcessingMapper;
import com.ruoyi.project.process.unitProcessing.service.UnitProcessingRepository;
import com.ruoyi.project.system.files.domain.Files;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/3
 * Time: 10:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MiddleStatusRepositoryTest {
    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private ArrangeTableMapper arrangeTableMapper;

    @Autowired
    private BatchProcessingRepository batchProcessingRepository;
    @Autowired
    private BatchProcessingMapper batchProcessingMapper;
    @Autowired
    private UnitProcessingRepository unitProcessingRepository;
    @Autowired
    private UnitProcessingMapper unitProcessingMapper;
    @Autowired
    private PipeProcessingRepository pipeProcessingRepository;
    @Autowired
    private PipeProcessingMapper pipeProcessingMapper;

    @Autowired
    private MiddleStatusRepository middleStatusRepository;
    @Autowired
    private MiddleStatusMapper middleStatusMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private TaoliaoMapper taoliaoMapper;


    @Test
    @Transactional
    public void arrangeTable() {
        List<ArrangeTable> objectList = arrangeTableRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = arrangeTableMapper.selectArrangeTableList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        ArrangeTable object = new ArrangeTable();
        object.setName("arrange");
        ArrangeTable object2 = arrangeTableRepository.save(object);
        int id = arrangeTableMapper.updateArrangeTable(object);

        object = arrangeTableRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setName("arrange2");
        id = arrangeTableMapper.updateArrangeTable(object);
        object = arrangeTableMapper.selectArrangeTableById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getName(),equalTo("arrange2"));
    }

    @Test
    @Transactional
    public void batchProcessing() {
        List<BatchProcessing> objectList = batchProcessingRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = batchProcessingMapper.selectBatchProcessingList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        BatchProcessing object = new BatchProcessing();
        object.setIsFinished(12);
        BatchProcessing object2 = batchProcessingRepository.save(object);
        int id = batchProcessingMapper.updateBatchProcessing(object);

        object = batchProcessingRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setIsFinished(1);
        id = batchProcessingMapper.updateBatchProcessing(object);
        object = batchProcessingMapper.selectBatchProcessingById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getIsFinished(),equalTo(1));
    }


    @Test
    @Transactional
    public void unitProcessing() {
        List<UnitProcessing> objectList = unitProcessingRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = unitProcessingMapper.selectUnitProcessingList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        UnitProcessing object = new UnitProcessing();
        object.setIsFinished(12);
        UnitProcessing object2 = unitProcessingRepository.save(object);
        int id = unitProcessingMapper.updateUnitProcessing(object);

        object = unitProcessingRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setIsFinished(1);
        id = unitProcessingMapper.updateUnitProcessing(object);
        object = unitProcessingMapper.selectUnitProcessingById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getIsFinished(),equalTo(1));
    }
    @Test
    @Transactional
    public void pipeProcessing() {
        List<PipeProcessing> objectList = pipeProcessingRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = pipeProcessingMapper.selectPipeProcessingList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        PipeProcessing object = new PipeProcessing();
        object.setIsFinished(12);
        PipeProcessing object2 = pipeProcessingRepository.save(object);
        int id = pipeProcessingMapper.updatePipeProcessing(object);

        object = pipeProcessingRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setIsFinished(1);
        id = pipeProcessingMapper.updatePipeProcessing(object);
        object = pipeProcessingMapper.selectPipeProcessingById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getIsFinished(),equalTo(1));
    }


    @Test
    @Transactional
    public void middleStatus() {
        List<MiddleStatus> objectList = middleStatusRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = middleStatusMapper.selectMiddleStatusList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        MiddleStatus object = new MiddleStatus();
        object.setIsRead(1);
        MiddleStatus object2 = middleStatusRepository.save(object);
        int id = middleStatusMapper.updateMiddleStatus(object);

        object = middleStatusRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setIsRead(0);
        id = middleStatusMapper.updateMiddleStatus(object);
        object = middleStatusMapper.selectMiddleStatusById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getIsRead(),equalTo(0));
    }


    @Test
    @Transactional
    public void processOrder() {
        List<Order> objectList = orderRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = orderMapper.selectOrderList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        Order object = new Order();
        object.setName("12");
        Order object2 = orderRepository.save(object);
        int id = orderMapper.updateOrder(object);

        object = orderRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setName("34");
        id = orderMapper.updateOrder(object);
        object = orderMapper.selectOrderById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getName(),equalTo("34"));
    }

    @Test
    @Transactional
    public void taoliao() {
        List<Taoliao> objectList = taoliaoRepository.findAll();
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        objectList = taoliaoMapper.selectTaoliaoList(null);
        Assert.assertThat(objectList,notNullValue());
        Assert.assertThat(objectList.size(),greaterThan(0));
        System.out.println("find size = "+objectList.size());

        Taoliao object = new Taoliao();
        object.setPipeMaterial("12");
        Taoliao object2 = taoliaoRepository.save(object);
        int id = taoliaoMapper.updateTaoliao(object);

        object = taoliaoRepository.findById(object2.getId()).orElse(null);
        Assert.assertThat(object,notNullValue());
        Assert.assertThat(object.getId(),equalTo(object2.getId()));

        object.setPipeMaterial("34");
        id = taoliaoMapper.updateTaoliao(object);
        object = taoliaoMapper.selectTaoliaoById(object2.getId());
        Assert.assertThat(id,greaterThan(0));
        Assert.assertThat(object.getPipeMaterial(),equalTo("34"));
    }

}