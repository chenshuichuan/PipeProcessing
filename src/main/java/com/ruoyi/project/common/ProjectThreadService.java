package com.ruoyi.project.common;


import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;

/**
 * Created by tengj on 2017/4/7.
 */
public interface ProjectThreadService {

    void executeGenerateAward(String model, int year);


    void judgeBatchUnitByPlanName(String planName);


    void cutArrange(ArrangeInfo arrangeInfo);
}
