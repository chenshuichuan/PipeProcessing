package com.ruoyi.project.process.unitProcessing.service;

import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import java.util.List;

/**
 * 单元加工记录 服务层
 * 
 * @author ricardo
 * @date 2019-03-08
 */
public interface IUnitProcessingService 
{
	/**
     * 查询单元加工记录信息
     * 
     * @param id 单元加工记录ID
     * @return 单元加工记录信息
     */
	public UnitProcessing selectUnitProcessingById(Integer id);
	
	/**
     * 查询单元加工记录列表
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 单元加工记录集合
     */
	public List<UnitProcessing> selectUnitProcessingList(UnitProcessing unitProcessing);
	
	/**
     * 新增单元加工记录
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 结果
     */
	public int insertUnitProcessing(UnitProcessing unitProcessing);
	
	/**
     * 修改单元加工记录
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 结果
     */
	public int updateUnitProcessing(UnitProcessing unitProcessing);
		
	/**
     * 删除单元加工记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteUnitProcessingByIds(String ids);
	
}
