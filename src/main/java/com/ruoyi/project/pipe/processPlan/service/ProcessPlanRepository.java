package com.ruoyi.project.pipe.processPlan.service;


import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface ProcessPlanRepository extends JpaRepository<ProcessPlan,Integer> {


}
