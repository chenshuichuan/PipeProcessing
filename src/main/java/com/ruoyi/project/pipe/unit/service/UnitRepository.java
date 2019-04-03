package com.ruoyi.project.pipe.unit.service;


import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.unit.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface UnitRepository extends JpaRepository<Unit,Integer> {
    /**
     * */
    Unit findByBatchNameAndName(String batchName, String name);
    List<Unit> findByBatchName(String batchName);
    List<Unit> findByNameLike(String unitName);
    List<Unit> findByBatchNameAndNameLike(String batchName, String name);
//    List<Unit> findByBatchNameAndPlanIdIsNull(String batchName);
    List<Unit> findByPlanId(int planId);
    List<Unit> findByPlanIdAndNextStageId(int planId,int nextStageId);
    List<Unit> findByShipCode(String shipCode);
    List<Unit> findByShipCodeAndBatchName(String shipCode,String batchName);
}
