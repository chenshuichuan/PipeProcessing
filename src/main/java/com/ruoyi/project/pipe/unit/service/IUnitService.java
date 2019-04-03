package com.ruoyi.project.pipe.unit.service;

import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.domain.UnitSimple;
import com.ruoyi.project.process.order.domain.Order;

import java.util.List;

/**
 * 加工单元 服务层
 *
 * @author ricardo
 * @date 2019-03-04
 */
public interface IUnitService {
    /**
     * 查询加工单元信息
     *
     * @param id 加工单元ID
     * @return 加工单元信息
     */
    public Unit selectUnitById(Integer id);

    /**
     * 查询加工单元列表
     *
     * @param unit 加工单元信息
     * @return 加工单元集合
     */
    public List<Unit> selectUnitList(Unit unit);

    /**
     * 新增加工单元
     *
     * @param unit 加工单元信息
     * @return 结果
     */
    public int insertUnit(Unit unit);

    /**
     * 修改加工单元
     *
     * @param unit 加工单元信息
     * @return 结果
     */
    public int updateUnit(Unit unit);

    /**
     * 删除加工单元信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUnitByIds(String ids);

    /**
     * 解析单元下所有管件加工顺序
     * 1.拿到所有管件对管件进行循环
     * 2.拿到管件对应的pipeCutting 和 workPipe数据
     * 3.对管件对应的加工顺序进行判断
     * @param unit 要解析顺序的单元
     */
    public Order analysisOrder(Unit unit);

    /**
     * 对加工计划涉及的所有单元进行解析
     */
    int analysisOrderByProcessPlan(ProcessPlan processPlan);

    /**
     * 根据shipName和batchName查找unitList
     * @param shipName
     * @param batchName
     * @return unitList
     */
    List<Unit> selectByShipNameAndBatchName(String shipName, String batchName);

    public List<UnitSimple> selectUnitSimpleByShipNameAndBatchName(String shipName, String batchName);
}
