package com.ruoyi.project.process.batchArrange.service;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.system.dict.domain.DictData;

import java.util.List;

/**
 * 批次派工 服务层
 * 
 * @author ricardo
 * @date 2019-03-09
 */
public interface IBatchArrangeService 
{
    /**
     * 根据派工情况安排下料派工
     * */
    public int arrangeCutPlan(ArrangeInfo arrangeInfo,CutPlan cutPlan);


}
