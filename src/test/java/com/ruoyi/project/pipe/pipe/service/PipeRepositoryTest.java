package com.ruoyi.project.pipe.pipe.service;

import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.batch.service.BatchRepository;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.CutPlanRepository;
import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipCutting.service.PipCuttingRepository;
import com.ruoyi.project.pipe.pipManage.domain.PipManage;
import com.ruoyi.project.pipe.pipManage.service.PipManageRepository;
import com.ruoyi.project.pipe.pipMaterial.domain.PipMaterial;
import com.ruoyi.project.pipe.pipMaterial.service.PipMaterialRepository;
import com.ruoyi.project.pipe.pipPipe.domain.PipPipe;
import com.ruoyi.project.pipe.pipPipe.service.PipPipeRepository;
import com.ruoyi.project.pipe.pipStore.domain.PipStore;
import com.ruoyi.project.pipe.pipStore.service.PipStoreRepository;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.ShipRepository;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.UnitRepository;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.pipe.workPipe.service.WorkPipeRepository;
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
 * Date: 2019/3/7
 * Time: 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PipeRepositoryTest {

    @Autowired
    private PipeRepository pipeRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private CutPlanRepository cutPlanRepository;
    @Autowired
    private PipCuttingRepository pipCuttingRepository;
    @Autowired
    private PipManageRepository pipManageRepository;
    @Autowired
    private PipMaterialRepository pipMaterialRepository;
    @Autowired
    private PipPipeRepository pipPipeRepository;

    @Autowired
    private PipStoreRepository pipStoreRepository;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private WorkPipeRepository workPipeRepository;
    @Autowired
    private UnitRepository unitRepository;

    @Test
    public void pipeRepository() {
        List<Pipe> pipeList = pipeRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void batchRepository() {
        List<Batch> pipeList = batchRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void cutPlanRepository() {
        List<CutPlan> pipeList = cutPlanRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void pipCuttingRepository() {
        List<PipCutting> pipeList = pipCuttingRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void pipManageRepository() {
        List<PipManage> pipeList = pipManageRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void pipMaterialRepository() {
        List<PipMaterial> pipeList = pipMaterialRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void pipPipeRepository() {
        List<PipPipe> pipeList = pipPipeRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void pipStoreRepository() {
        List<PipStore> pipeList = pipStoreRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }

    @Test
    public void processPlanRepository() {
        List<ProcessPlan> pipeList = processPlanRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void shipRepository() {
        List<Ship> pipeList = shipRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void workPipeRepository() {
        List<WorkPipe> pipeList = workPipeRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        //Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
    @Test
    public void unitRepository() {
        List<Unit> pipeList = unitRepository.findAll();
        Assert.assertThat(pipeList,notNullValue());
        Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());

        pipeList = unitRepository.findByBatchNameAndNameLike("G01","M%");

        Assert.assertThat(pipeList,notNullValue());
        Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());

        pipeList = unitRepository.findByBatchNameAndNameLike("G01","%");
        Assert.assertThat(pipeList,notNullValue());
        Assert.assertThat(pipeList.size(),greaterThan(0));
        System.out.println("find size = "+pipeList.size());
    }
}