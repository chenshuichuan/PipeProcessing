package com.ruoyi.project.process.unitProcessing.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface UnitProcessingRepository extends JpaRepository<UnitProcessing,Integer> {


}
