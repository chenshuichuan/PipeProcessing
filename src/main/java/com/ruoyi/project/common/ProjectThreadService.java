package com.ruoyi.project.common;



/**
 * Created by tengj on 2017/4/7.
 */
public interface ProjectThreadService {

    void executeGenerateAward(String model, int year);


    void judgeBatchUnitByPlanName(String planName);
}
