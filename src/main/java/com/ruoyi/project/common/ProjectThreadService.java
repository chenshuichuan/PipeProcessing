package com.ruoyi.project.common;


import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.order.domain.ProcessStage;

/**
 * Created by tengj on 2017/4/7.
 */
public interface ProjectThreadService {

    void executeGenerateAward(String model, int year);


    void judgeBatchUnitByPlanName(String planName);


    void cutArrange(ArrangeInfo arrangeInfo);

}
