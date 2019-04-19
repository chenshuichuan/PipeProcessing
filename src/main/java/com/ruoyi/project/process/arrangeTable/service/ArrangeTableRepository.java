package com.ruoyi.project.process.arrangeTable.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface ArrangeTableRepository extends JpaRepository<ArrangeTable,Integer> {
    /**
     * 获取下料的唯一派工单
     * 用于构建该派工单的下料管材
     * **/
    ArrangeTable findByTypeAndPlanIdAndStageAndWorkplace(Integer type, Integer planId, String Stage, String workpalce);
}
