package com.ruoyi.project.process.unitArrange.service;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;

import java.util.List;

/**
 * 单元派工 服务层
 * 
 * @author ricardo
 * @date 2019-03-09
 */
public interface IUnitArrangeService 
{
    //下料派工看下料计划，大岗下料、一部下料、二部下料
    //钢管 外径$140以上 在大岗下料，$114 以下在一部，特殊管在 二部（$22~60: 小管，$76~168:中管，$219+:大管）所以中管有的会在一部下料，有的在大岗下料

    /**
     * 安排单元加工
     * @param unit
     * @param arrangeTable
     * @return int
     * **/
    int arrangeUnit(Unit unit, ArrangeTable arrangeTable);
    /**
     * 安排单元下料
     * @param unit
     * @param arrangeTable
     * @return int
     * **/
    int arrangeUnitCut(Unit unit, ArrangeTable arrangeTable);
    /**
     * 安排单元加工----多派工单---下料派工可能出现的情况
     * @param unit
     * @param onebigSection
     * @return int
     * **/
    int arrangeUnitCut(Unit unit, ArrangeTable onebigSection,ArrangeTable oneSection,ArrangeTable twoSection);

}
