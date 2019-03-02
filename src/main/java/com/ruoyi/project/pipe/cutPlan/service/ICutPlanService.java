package com.ruoyi.project.pipe.cutPlan.service;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import java.util.List;

/**
 * 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan 服务层
 * 
 * @author ricardo
 * @date 2019-03-02
 */
public interface ICutPlanService 
{
	/**
     * 查询加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * 
     * @param id 加工计划，不包含下料计划，下料计划请查看pipe_cut_planID
     * @return 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     */
	public CutPlan selectCutPlanById(Integer id);
	
	/**
     * 查询加工计划，不包含下料计划，下料计划请查看pipe_cut_plan列表
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan集合
     */
	public List<CutPlan> selectCutPlanList(CutPlan cutPlan);
	
	/**
     * 新增加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 结果
     */
	public int insertCutPlan(CutPlan cutPlan);
	
	/**
     * 修改加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
     * 
     * @param cutPlan 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * @return 结果
     */
	public int updateCutPlan(CutPlan cutPlan);
		
	/**
     * 删除加工计划，不包含下料计划，下料计划请查看pipe_cut_plan信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCutPlanByIds(String ids);
	
}
