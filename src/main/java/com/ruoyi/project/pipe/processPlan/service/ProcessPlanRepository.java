package com.ruoyi.project.pipe.processPlan.service;


import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface ProcessPlanRepository extends JpaRepository<ProcessPlan,Integer> {

    /**
     *  通过“序号+船名+批次+加工点”作为唯一数据判断，防止重复解析计划
     */
    ProcessPlan findBySerialNumberAndShipNameAndBatchNameAndProcessPlace(String serialNumber,
                                                                             String shipName,String batchName,String processPlace);
    /**
     * 查找某计划名称下所有的计划
     * @param planName 要查找的计划名称
     * */
    List<ProcessPlan> findByPlanName(String planName);
}
