package com.ruoyi.project.process.arrangeTable.service;

import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import java.util.List;

/**
 * 派工记录 服务层
 * 
 * @author ricardo
 * @date 2019-03-08
 */
public interface IArrangeTableService 
{
	/**
     * 查询派工记录信息
     * 
     * @param id 派工记录ID
     * @return 派工记录信息
     */
	public ArrangeTable selectArrangeTableById(Integer id);
	
	/**
     * 查询派工记录列表
     * 
     * @param arrangeTable 派工记录信息
     * @return 派工记录集合
     */
	public List<ArrangeTable> selectArrangeTableList(ArrangeTable arrangeTable);
	
	/**
     * 新增派工记录
     * 
     * @param arrangeTable 派工记录信息
     * @return 结果
     */
	public int insertArrangeTable(ArrangeTable arrangeTable);
	
	/**
     * 修改派工记录
     * 
     * @param arrangeTable 派工记录信息
     * @return 结果
     */
	public int updateArrangeTable(ArrangeTable arrangeTable);
		
	/**
     * 删除派工记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteArrangeTableByIds(String ids);
	
}
