package com.ruoyi.project.process.batchProcessing.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface BatchProcessingRepository extends JpaRepository<BatchProcessing,Integer> {


}
