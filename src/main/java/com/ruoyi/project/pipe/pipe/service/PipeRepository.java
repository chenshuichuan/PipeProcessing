package com.ruoyi.project.pipe.pipe.service;


import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface PipeRepository extends JpaRepository<Pipe,Integer> {
    List<Pipe> findByBatchIdAndUnitId(Integer batchId, Integer unitId);
}
