package com.ruoyi.project.pipe.batch.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface BatchRepository extends JpaRepository<Batch,Integer> {


}
