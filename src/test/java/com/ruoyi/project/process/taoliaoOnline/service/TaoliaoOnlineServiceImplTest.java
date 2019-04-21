package com.ruoyi.project.process.taoliaoOnline.service;

import com.ruoyi.project.algorithm.AlgorithmConstants;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.mapper.ArrangeTableMapper;
import com.ruoyi.project.process.arrangeTable.service.ArrangeTableRepository;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.mapper.TaoliaoMapper;
import com.ruoyi.project.process.taoliao.service.TaoliaoRepository;
import com.ruoyi.project.process.taoliaoOnline.domain.OriginInfo;
import com.ruoyi.project.process.taoliaoOrigin.mapper.TaoliaoOriginMapper;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.mapper.TaoliaoResultMapper;
import com.ruoyi.project.process.taoliaoResult.service.TaoliaoResultRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/20
 * Time: 16:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaoliaoOnlineServiceImplTest {
    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private TaoliaoMapper taoliaoMapper;
    @Autowired
    private TaoliaoOriginMapper taoliaoOriginMapper;
    @Autowired
    private TaoliaoResultMapper taoliaoResultMapper;
    @Autowired
    private ArrangeTableRepository arrangeTableRepository;
    @Autowired
    private ArrangeTableMapper arrangeTableMapper;
    @Autowired
    private ITaoliaoOnlineService taoliaoOnlineService;
    @Autowired
    private TaoliaoResultRepository taoliaoResultRepository;

    @Transactional
    @Test
    public void generateTaoliao() {

        Integer arrangeId = 27;
        ArrangeTable arrangeTable = arrangeTableMapper.selectArrangeTableById(arrangeId);
        taoliaoOnlineService.generateTaoliao(arrangeTable);

        List<Taoliao> taoliaoList = taoliaoRepository.findAll();
        Assert.assertThat(taoliaoList,notNullValue());
        Assert.assertThat(taoliaoList.size(),greaterThan(0));
        System.out.println("find size = "+taoliaoList.size());

        List<TaoliaoResult> taoliaoResultList = taoliaoResultRepository.findAll();
        Assert.assertThat(taoliaoResultList,notNullValue());
        Assert.assertThat(taoliaoResultList.size(),greaterThan(0));
        System.out.println("find size = "+taoliaoResultList.size());
    }

    private void forTestdynamicProgramming(Integer taoliaoId, String algorithm, List<OriginInfo> originInfoList){
        Taoliao taoliao = taoliaoMapper.selectTaoliaoById(taoliaoId);
        taoliaoOnlineService.deleteOrigin(taoliao, AlgorithmConstants.DynamicProgrammingName);
        taoliaoOnlineService.inputOrigin(taoliao,originInfoList,AlgorithmConstants.DynamicProgrammingName);
        taoliaoOnlineService.dynamicProgramming(taoliao);
    }
    @Transactional
    @Test
    public void dynamicProgramming1() {
        Integer taoliaoId = 141;
        List<OriginInfo> originInfoList = new ArrayList<>();
        originInfoList.add(new OriginInfo(1000,10));
        forTestdynamicProgramming(taoliaoId,AlgorithmConstants.DynamicProgrammingName,originInfoList);
    }
    @Transactional
    @Test
    public void dynamicProgramming2() {
        Integer taoliaoId = 141;
        List<OriginInfo> originInfoList = new ArrayList<>();
        originInfoList.add(new OriginInfo(40000,2));
        forTestdynamicProgramming(taoliaoId,AlgorithmConstants.DynamicProgrammingName,originInfoList);
    }
    @Transactional
    @Test
    public void dynamicProgramming3() {
        Integer taoliaoId = 141;
        List<OriginInfo> originInfoList = new ArrayList<>();
        originInfoList.add(new OriginInfo(4000,1));
        originInfoList.add(new OriginInfo(6000,1));
        originInfoList.add(new OriginInfo(5000,1));

        forTestdynamicProgramming(taoliaoId,AlgorithmConstants.DynamicProgrammingName,originInfoList);
    }

    @Transactional
    @Test
    public void dynamicProgramming4() {
        Integer taoliaoId = 141;
        List<OriginInfo> originInfoList = new ArrayList<>();
        originInfoList.add(new OriginInfo(4000,1));
        originInfoList.add(new OriginInfo(5000,1));
        forTestdynamicProgramming(taoliaoId,AlgorithmConstants.DynamicProgrammingName,originInfoList);
    }

    @Transactional
    @Test
    public void dynamicProgramming5() {
        Integer taoliaoId = 141;
        List<OriginInfo> originInfoList = new ArrayList<>();
        originInfoList.add(new OriginInfo(4000,2));
        originInfoList.add(new OriginInfo(5000,1));
        forTestdynamicProgramming(taoliaoId,AlgorithmConstants.DynamicProgrammingName,originInfoList);
    }
}