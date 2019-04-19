package com.ruoyi.project.process.taoliaoOrigin.service;

import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import java.util.List;

/**
 * 套料原材料 服务层
 * 
 * @author ricardo
 * @date 2019-04-19
 */
public interface ITaoliaoOriginService 
{
	/**
     * 查询套料原材料信息
     * 
     * @param id 套料原材料ID
     * @return 套料原材料信息
     */
	public TaoliaoOrigin selectTaoliaoOriginById(Integer id);
	
	/**
     * 查询套料原材料列表
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 套料原材料集合
     */
	public List<TaoliaoOrigin> selectTaoliaoOriginList(TaoliaoOrigin taoliaoOrigin);
	
	/**
     * 新增套料原材料
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 结果
     */
	public int insertTaoliaoOrigin(TaoliaoOrigin taoliaoOrigin);
	
	/**
     * 修改套料原材料
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 结果
     */
	public int updateTaoliaoOrigin(TaoliaoOrigin taoliaoOrigin);
		
	/**
     * 删除套料原材料信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaoliaoOriginByIds(String ids);
	
}
