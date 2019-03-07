package com.ruoyi.project.system.files.service;

import com.ruoyi.common.utils.poi.ReadPlanTable;
import com.ruoyi.project.pipe.common.PlanTable;
import com.ruoyi.project.pipe.cutPlan.service.CutPlanRepository;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.project.pipe.processPlan.service.ProcessPlanRepository;
import com.ruoyi.project.system.files.domain.Files;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/3/2
 * Time: 23:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FilesRepositoryTest {

    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private IProcessPlanService processPlanService;
    @Autowired
    private ICutPlanService cutPlanService;
    @Autowired
    private CutPlanRepository cutPlanRepository;
    @Autowired
    private ProcessPlanRepository processPlanRepository;
    @Test
    public void findBySuffix() {
        List<Files> filesList = filesRepository.findBySuffix("xls");
        Assert.assertThat(filesList,notNullValue());
        Assert.assertThat(filesList.size(),greaterThan(0));
        System.out.println("find size = "+filesList.size());
    }
    @Test
    public void readXlsFile() {
        Optional<Files> optionalFiles = filesRepository.findById(78);
        Assert.assertThat(optionalFiles.isPresent(),notNullValue());
        Files files = optionalFiles.get();
        Assert.assertThat(files,notNullValue());
        try {
             List<PlanTable> planTableList = ReadPlanTable.readExcel(files);
            int i=0;
            for (PlanTable planTable: planTableList) {

                ProcessPlan processPlan = processPlanRepository.save(planTable.getProcessPlan());
                if(processPlan!=null){
                    planTable.getCutPlan().setId(processPlan.getId());
                    cutPlanRepository.save(planTable.getCutPlan());
                }
//                processPlanService.insertProcessPlan(planTable.getProcessPlan());
//                cutPlanService.insertCutPlan(planTable.getCutPlan());
//                System.out.println(i++);
//                System.out.println(planTable);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }
}