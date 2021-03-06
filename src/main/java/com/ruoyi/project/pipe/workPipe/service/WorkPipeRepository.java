package com.ruoyi.project.pipe.workPipe.service;


import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author ricardo
 * Created by: ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface WorkPipeRepository extends JpaRepository<WorkPipe,Integer> {
    List<WorkPipe> findByAssemblyPipeIdAndShapeShipId(Integer assemblyPipeId, Integer shapeShipId);
    List<WorkPipe> findByAssemblyPipeIdAndShapeShipIdAndCutLength(Integer assemblyPipeId, Integer shapeShipId,Integer cutLength);
}
