package com.ruoyi.project.pipe.pipCutting.service;


import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface PipCuttingRepository extends JpaRepository<PipCutting,Integer> {


}
