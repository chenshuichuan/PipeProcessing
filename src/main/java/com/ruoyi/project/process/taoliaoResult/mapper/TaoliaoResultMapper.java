package com.ruoyi.project.process.taoliaoResult.mapper;

import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import java.util.List;	

/**
 * 管材套料结果 数据层
 * 
 * @author ricardo
 * @date 2019-04-19
 */
public interface TaoliaoResultMapper 
{
	/**
     * 查询管材套料结果信息
     * 
     * @param id 管材套料结果ID
     * @return 管材套料结果信息
     */
	public TaoliaoResult selectTaoliaoResultById(Integer id);
	
	/**
     * 查询管材套料结果列表
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 管材套料结果集合
     */
	public List<TaoliaoResult> selectTaoliaoResultList(TaoliaoResult taoliaoResult);
	
	/**
     * 新增管材套料结果
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 结果
     */
	public int insertTaoliaoResult(TaoliaoResult taoliaoResult);
	
	/**
     * 修改管材套料结果
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 结果
     */
	public int updateTaoliaoResult(TaoliaoResult taoliaoResult);
	
	/**
     * 删除管材套料结果
     * 
     * @param id 管材套料结果ID
     * @return 结果
     */
	public int deleteTaoliaoResultById(Integer id);
	
	/**
     * 批量删除管材套料结果
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaoliaoResultByIds(String[] ids);
	
}