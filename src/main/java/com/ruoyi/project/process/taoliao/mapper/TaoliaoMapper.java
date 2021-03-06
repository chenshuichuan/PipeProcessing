package com.ruoyi.project.process.taoliao.mapper;

import com.ruoyi.project.process.taoliao.domain.Taoliao;
import java.util.List;	

/**
 * 套料管材 数据层
 * 
 * @author ricardo
 * @date 2019-04-19
 */
public interface TaoliaoMapper 
{
	/**
     * 查询套料管材信息
     * 
     * @param id 套料管材ID
     * @return 套料管材信息
     */
	public Taoliao selectTaoliaoById(Integer id);
	
	/**
     * 查询套料管材列表
     * 
     * @param taoliao 套料管材信息
     * @return 套料管材集合
     */
	public List<Taoliao> selectTaoliaoList(Taoliao taoliao);
	
	/**
     * 新增套料管材
     * 
     * @param taoliao 套料管材信息
     * @return 结果
     */
	public int insertTaoliao(Taoliao taoliao);
	
	/**
     * 修改套料管材
     * 
     * @param taoliao 套料管材信息
     * @return 结果
     */
	public int updateTaoliao(Taoliao taoliao);
	
	/**
     * 删除套料管材
     * 
     * @param id 套料管材ID
     * @return 结果
     */
	public int deleteTaoliaoById(Integer id);
	
	/**
     * 批量删除套料管材
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTaoliaoByIds(String[] ids);
	
}