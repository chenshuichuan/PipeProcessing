package com.ruoyi.project.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Autowired
    private ProjectThreadService projectThreadService;

    @Test
    public void executeGenerateAward() {
        for (int i = 0; i < 5; i++) {
            projectThreadService.executeGenerateAward("model1",2019+i);
        }

    }



}