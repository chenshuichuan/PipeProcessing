package com.ruoyi.project.process.unitArrange.service;

import com.ruoyi.project.process.unitArrange.domain.UnitArrange;
import java.util.List;

/**
 * 单元派工 服务层
 * 
 * @author ricardo
 * @date 2019-03-09
 */
public interface IUnitArrangeService 
{
	/**
     * 查询单元派工信息
     * 
     * @param id 单元派工ID
     * @return 单元派工信息
     */
	public UnitArrange selectUnitArrangeById(Integer id);
	
	/**
     * 查询单元派工列表
     * 
     * @param unitArrange 单元派工信息
     * @return 单元派工集合
     */
	public List<UnitArrange> selectUnitArrangeList(UnitArrange unitArrange);
	
	/**
     * 新增单元派工
     * 
     * @param unitArrange 单元派工信息
     * @return 结果
     */
	public int insertUnitArrange(UnitArrange unitArrange);
	
	/**
     * 修改单元派工
     * 
     * @param unitArrange 单元派工信息
     * @return 结果
     */
	public int updateUnitArrange(UnitArrange unitArrange);
		
	/**
     * 删除单元派工信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUnitArrangeByIds(String ids);
	
}
