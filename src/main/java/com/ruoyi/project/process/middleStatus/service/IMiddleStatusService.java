package com.ruoyi.project.process.middleStatus.service;

import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import java.util.List;

/**
 * 中间数据读取程序状态 服务层
 * 
 * @author ricardo
 * @date 2019-04-01
 */
public interface IMiddleStatusService 
{
	/**
     * 查询中间数据读取程序状态信息
     * 
     * @param id 中间数据读取程序状态ID
     * @return 中间数据读取程序状态信息
     */
	public MiddleStatus selectMiddleStatusById(Integer id);
	
	/**
     * 查询中间数据读取程序状态列表
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 中间数据读取程序状态集合
     */
	public List<MiddleStatus> selectMiddleStatusList(MiddleStatus middleStatus);
	
	/**
     * 新增中间数据读取程序状态
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 结果
     */
	public int insertMiddleStatus(MiddleStatus middleStatus);
	
	/**
     * 修改中间数据读取程序状态
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 结果
     */
	public int updateMiddleStatus(MiddleStatus middleStatus);
		
	/**
     * 删除中间数据读取程序状态信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMiddleStatusByIds(String ids);
	
}
