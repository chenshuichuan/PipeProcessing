package com.ruoyi.project.common;


import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.batchArrange.domain.ArrangeInfo;
import com.ruoyi.project.process.order.domain.ProcessStage;

/**
 * 用于单元派工的异步线程
 */
public interface UnitThreadService {


    void unitArrange(Unit unit, ArrangeTable arrangeTable, ProcessStage nextSatge);
}
