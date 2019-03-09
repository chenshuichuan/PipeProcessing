package com.ruoyi.project.process.pipeProcessing.service;


import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author  ricardo
 * Description: 下料类的repository类
 * Date: 2018/8/22
 */
public interface PipeProcessingRepository extends JpaRepository<PipeProcessing,Integer> {


}
