package com.ruoyi.project.process.unitArrange.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.unitArrange.mapper.UnitArrangeMapper;
import com.ruoyi.project.process.unitArrange.domain.UnitArrange;
import com.ruoyi.project.process.unitArrange.service.IUnitArrangeService;
import com.ruoyi.common.support.Convert;

/**
 * 单元派工 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-09
 */
@Service
public class UnitArrangeServiceImpl implements IUnitArrangeService 
{
	@Autowired
	private UnitArrangeMapper unitArrangeMapper;

	/**
     * 查询单元派工信息
     * 
     * @param id 单元派工ID
     * @return 单元派工信息
     */
    @Override
	public UnitArrange selectUnitArrangeById(Integer id)
	{
	    return unitArrangeMapper.selectUnitArrangeById(id);
	}
	
	/**
     * 查询单元派工列表
     * 
     * @param unitArrange 单元派工信息
     * @return 单元派工集合
     */
	@Override
	public List<UnitArrange> selectUnitArrangeList(UnitArrange unitArrange)
	{
	    return unitArrangeMapper.selectUnitArrangeList(unitArrange);
	}
	
    /**
     * 新增单元派工
     * 
     * @param unitArrange 单元派工信息
     * @return 结果
     */
	@Override
	public int insertUnitArrange(UnitArrange unitArrange)
	{
	    return unitArrangeMapper.insertUnitArrange(unitArrange);
	}
	
	/**
     * 修改单元派工
     * 
     * @param unitArrange 单元派工信息
     * @return 结果
     */
	@Override
	public int updateUnitArrange(UnitArrange unitArrange)
	{
	    return unitArrangeMapper.updateUnitArrange(unitArrange);
	}

	/**
     * 删除单元派工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUnitArrangeByIds(String ids)
	{
		return unitArrangeMapper.deleteUnitArrangeByIds(Convert.toStrArray(ids));
	}
	
}
