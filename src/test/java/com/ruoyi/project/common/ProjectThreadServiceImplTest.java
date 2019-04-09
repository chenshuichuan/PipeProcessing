package com.ruoyi.project.common;

import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/2
 * Time: 20:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectThreadServiceImplTest {
    private static final Logger logger=LoggerFactory.getLogger(ProjectThreadServiceImplTest.class);
    @Autowired
    private ProjectThreadService projectThreadService;
    @Autowired
    private IBatchArrangeService batchArrangeService;

    @Test
    public void executeGenerateAward() {
        for (int i = 0; i < 5; i++) {
            projectThreadService.executeGenerateAward("model1",2019+i);
        }

    }


    @Test
    public void cutArrange1() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(773,1,null,
                1,null,null,132,null);
        //projectThreadService.cutArrange(arrangeInfo);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
    @Transactional
    @Test
    public void cutArrange2() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(780,4,4,
                null,null,179,null,null);
        projectThreadService.cutArrange(arrangeInfo);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
    @Transactional
    @Test
    public void cutArrange3() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(777,38,17,
                21,null,179,132,null);
        projectThreadService.cutArrange(arrangeInfo);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
}