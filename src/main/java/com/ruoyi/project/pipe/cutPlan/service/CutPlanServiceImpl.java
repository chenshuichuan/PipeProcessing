package com.ruoyi.project.pipe.cutPlan.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.cutPlan.mapper.CutPlanMapper;
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.common.support.Convert;

/**
 * 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-02
 */
@Service
public class CutPlanServiceImpl implements ICutPlanService 
{
	@Autowired
	private CutPlanMapper cutPlanMapper;

	/**
     * 查询加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * 
     * @param id 加工计划，不包含下料计划，下料计划请查看pipe_cut_planID
     * @return 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     */
    @Override
	public CutPlan selectCutPlanById(Integer id)
	{
	    return cutPlanMapper.selectCutPlanById(id);
	}
	
	/**
     * 查询加工计划，不包含下料计划，下料计划请查看pipe_cut_plan列表
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan集合
     */
	@Override
	public List<CutPlan> selectCutPlanList(CutPlan cutPlan)
	{
	    return cutPlanMapper.selectCutPlanList(cutPlan);
	}
	
    /**
     * 新增加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 结果
     */
	@Override
	public int insertCutPlan(CutPlan cutPlan)
	{
	    return cutPlanMapper.insertCutPlan(cutPlan);
	}
	
	/**
     * 修改加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 结果
     */
	@Override
	public int updateCutPlan(CutPlan cutPlan)
	{
	    return cutPlanMapper.updateCutPlan(cutPlan);
	}

	/**
     * 删除加工计划，不包含下料计划，下料计划请查看pipe_cut_plan对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCutPlanByIds(String ids)
	{
		return cutPlanMapper.deleteCutPlanByIds(Convert.toStrArray(ids));
	}
	
}
