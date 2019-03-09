package com.ruoyi.project.process.batchArrange.service;

import java.util.List;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.mapper.CutPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.batchArrange.domain.BatchArrange;
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.common.support.Convert;

/**
 * 批次派工 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-09
 */
@Service
public class BatchArrangeServiceImpl implements IBatchArrangeService 
{
	@Autowired
	private CutPlanMapper cutPlanMapper;


	
}
