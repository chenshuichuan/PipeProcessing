package com.ruoyi.project.pipe.unit.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.unit.mapper.UnitMapper;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.common.support.Convert;

/**
 * 加工单元 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class UnitServiceImpl implements IUnitService 
{
	@Autowired
	private UnitMapper unitMapper;

	/**
     * 查询加工单元信息
     * 
     * @param id 加工单元ID
     * @return 加工单元信息
     */
    @Override
	public Unit selectUnitById(Integer id)
	{
	    return unitMapper.selectUnitById(id);
	}
	
	/**
     * 查询加工单元列表
     * 
     * @param unit 加工单元信息
     * @return 加工单元集合
     */
	@Override
	public List<Unit> selectUnitList(Unit unit)
	{
	    return unitMapper.selectUnitList(unit);
	}
	
    /**
     * 新增加工单元
     * 
     * @param unit 加工单元信息
     * @return 结果
     */
	@Override
	public int insertUnit(Unit unit)
	{
	    return unitMapper.insertUnit(unit);
	}
	
	/**
     * 修改加工单元
     * 
     * @param unit 加工单元信息
     * @return 结果
     */
	@Override
	public int updateUnit(Unit unit)
	{
	    return unitMapper.updateUnit(unit);
	}

	/**
     * 删除加工单元对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUnitByIds(String ids)
	{
		return unitMapper.deleteUnitByIds(Convert.toStrArray(ids));
	}
	
}
