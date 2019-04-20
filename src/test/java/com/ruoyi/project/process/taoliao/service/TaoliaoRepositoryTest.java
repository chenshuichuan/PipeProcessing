package com.ruoyi.project.process.taoliao.service;

import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.mapper.ArrangeTableMapper;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliaoOnline.service.ITaoliaoOnlineService;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.service.TaoliaoResultRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/19
 * Time: 21:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaoliaoRepositoryTest {

    @Autowired
    private TaoliaoRepository taoliaoRepository;
    @Autowired
    private ITaoliaoService taoliaoService;
    @Autowired
    private ArrangeTableMapper arrangeTableMapper;
    @Autowired
    private ITaoliaoOnlineService taoliaoOnlineService;
    @Autowired
    private TaoliaoResultRepository taoliaoResultRepository;

    @Transactional
    @Test
    public void deleteByArrangeId() {
        Integer arrangId = 1;
        Integer palnId = 1;
        List<Taoliao> taoliaoList = taoliaoRepository.findByArrangeIdAndPlanId(arrangId,palnId);
        Assert.assertThat(taoliaoList,notNullValue());
        Assert.assertThat(taoliaoList.size(),greaterThan(0));
        System.out.println("find size = "+taoliaoList.size());

        int i = taoliaoRepository.deleteByArrangeId(arrangId);
        System.out.println("i="+i);
        taoliaoList = taoliaoRepository.findByArrangeIdAndPlanId(arrangId,palnId);
        Assert.assertThat(taoliaoList,notNullValue());
        Assert.assertThat(taoliaoList.size(),equalTo(0));
        System.out.println("find size = "+taoliaoList.size());
    }

    @Test
    public void findByArrangeIdAndPlanId() {
    }

    //@Transactional
    @Test
    public void deleteByArrangeTable() {
        Integer arrangeId = 27;
        ArrangeTable arrangeTable = arrangeTableMapper.selectArrangeTableById(arrangeId);

        taoliaoService.deleteTaoliaoByArrangeTable(arrangeTable);

        List<Taoliao> taoliaoList = taoliaoRepository.findAll();
        Assert.assertThat(taoliaoList,notNullValue());
        Assert.assertThat(taoliaoList.size(),equalTo(2));
        System.out.println("find size = "+taoliaoList.size());

        List<TaoliaoResult> taoliaoResultList = taoliaoResultRepository.findAll();
        Assert.assertThat(taoliaoResultList,notNullValue());
        Assert.assertThat(taoliaoResultList.size(),equalTo(1));
        System.out.println("find size = "+taoliaoResultList.size());
    }
}