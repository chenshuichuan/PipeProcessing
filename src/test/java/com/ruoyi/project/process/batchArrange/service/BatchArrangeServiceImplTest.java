package com.ruoyi.project.process.batchArrange.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.system.user.controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Filter;

import static org.junit.Assert.*;

/**
 * Created by:Ricardo
 * Description:
 * Date: 2019/4/8
 * Time: 0:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchArrangeServiceImplTest {
    private static final Logger logger=LoggerFactory.getLogger(BatchArrangeServiceImplTest.class);
    @Autowired
    private IBatchArrangeService batchArrangeService;
    @Autowired
    private ICutPlanService cutPlanService;

    @Autowired
    private LoginController loginController;
    private MockMvc mockMvc;
    private MockMvc mockMvc2;
    private MockHttpSession session;
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.standaloneSetup(loginController).addFilters((Filter) SpringUtils.getBean("shiroFilter")).build();
//        mockMvc2 = MockMvcBuilders.standaloneSetup(loginController).addFilters((Filter) SpringUtils.getBean("shiroFilter")).build();
//        this.session = doLogin();
//    }
//
//    /**
//     * 获取用户登录的Session
//     * @return MockHttpSession
//     * @throws Exception 异常
//     */
//    private MockHttpSession doLogin() throws Exception {
//        ResultActions resultActions = this.mockMvc2.perform(MockMvcRequestBuilders.post("/admin/login")
//                .param("username", "admin").param("password", "123456"));
//        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//        MvcResult result = resultActions.andReturn();
//        session = (MockHttpSession) result.getRequest().getSession();
//        return session;
//    }

    @Transactional
    @Test
    public void arrangeCutPlan() {
        ArrangeInfo arrangeInfo = new ArrangeInfo();
        arrangeInfo.setId(773);
        arrangeInfo.setTotalCutNumber(1);
        arrangeInfo.setOneTotalNumber(1);
        arrangeInfo.setOneCutId(134);
    }

    @Transactional
    @Test
    public void cutArrange1() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(773,1,null,
                1,null,null,132,null);
        CutPlan cutPlan = cutPlanService.selectCutPlanById(arrangeInfo.getId());
        //更新该cutPlan 的呗下料状态
        cutPlan.setIsArrange(1);
        cutPlanService.updateCutPlan(cutPlan);

        batchArrangeService.arrangeCutPlan(arrangeInfo, cutPlan);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
    @Transactional
    @Test
    public void cutArrange2() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(780,4,4,
                null,null,179,null,null);
        CutPlan cutPlan = cutPlanService.selectCutPlanById(arrangeInfo.getId());
        //更新该cutPlan 的呗下料状态
        cutPlan.setIsArrange(1);
        cutPlanService.updateCutPlan(cutPlan);

        batchArrangeService.arrangeCutPlan(arrangeInfo, cutPlan);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
    @Transactional
    @Test
    public void cutArrange3() {
        ArrangeInfo arrangeInfo = new ArrangeInfo(777,38,17,
                21,null,179,132,null);

        CutPlan cutPlan = cutPlanService.selectCutPlanById(arrangeInfo.getId());
        //更新该cutPlan 的呗下料状态
        cutPlan.setIsArrange(1);
        cutPlanService.updateCutPlan(cutPlan);

        batchArrangeService.arrangeCutPlan(arrangeInfo, cutPlan);
        System.out.println("cutArrange1");
        logger.info("cutArrange1");
    }
}