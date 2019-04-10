package com.ruoyi.project.process.unitArrange.service;

import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.process.unitArrange.domain.UnitArrangeInfo;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/10
 * Time: 13:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitArrangeServiceImplTest {

    private static final Logger logger=LoggerFactory.getLogger(UnitArrangeServiceImplTest.class);
    @Autowired
    private IUnitArrangeService unitArrangeService;
    @Autowired
    private IWorkplaceService  workplaceService;
    @Autowired
    private IUnitService unitService;

    @Transactional
    @Test
    public void arrangeByUniteArrangInfo() {
        //UnitArrangeInfo{id=48, name='LG001', shipCode='14664001', batchId=null, batchName='G01',
        // processOrder='下料->校管', processStage='下料', nextStage='下料', isFinished=null, isArrange=null,
        // workPlaceId=157, processSectionId=118, processSection='校管工位1-空闲'}

        UnitArrangeInfo unitArrangeInfo = new UnitArrangeInfo(48,"LG001","14664001",null,
                "G01","下料->校管","下料","校管",null,
                1,157,118,"校管工位1-空闲");
        unitArrangeService.arrangeByUniteArrangInfo(unitArrangeInfo);

        Unit unit = unitService.selectUnitById(unitArrangeInfo.getId());
        Assert.assertThat(unit,notNullValue());
        Assert.assertThat(unit.getIsArrange(),equalTo(0));

    }

    @Test
    public void arrangeUnit() {
    }

    @Test
    public void arrangeUnitCut() {
    }

    @Test
    public void arrangeUnitCut1() {
    }
}