package com.ruoyi.project.pipe.processPlan.service;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import java.util.List;

/**
 * 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan 服务层
 * 
 * @author ricardo
 * @date 2019-03-02
 */
public interface IProcessPlanService 
{
	/**
     * 查询下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * 
     * @param id 下料计划，不包含后续加工，后续加工计划请查看pipe_process_planID
     * @return 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     */
	public ProcessPlan selectProcessPlanById(Integer id);
	
	/**
     * 查询下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan列表
     * 
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan集合
     */
	public List<ProcessPlan> selectProcessPlanList(ProcessPlan processPlan);
	
	/**
     * 新增下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
     * 
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 结果
     */
	public int insertProcessPlan(ProcessPlan processPlan);
	
	/**
     * 修改下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
     * 
     * @param processPlan 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * @return 结果
     */
	public int updateProcessPlan(ProcessPlan processPlan);
		
	/**
     * 删除下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteProcessPlanByIds(String ids);

	/**
	 * 判断某加工计划下包含的单元、管件
	 * @param processPlan 要分析判断的计划
	 * @return int 分析成功返回1，否则返回0
	 * */
	public int judgeBatchUnitOfPlan(ProcessPlan processPlan);
}
