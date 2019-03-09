package com.ruoyi.project.process.unitProcessing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.unitProcessing.mapper.UnitProcessingMapper;
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import com.ruoyi.project.process.unitProcessing.service.IUnitProcessingService;
import com.ruoyi.common.support.Convert;

/**
 * 单元加工记录 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class UnitProcessingServiceImpl implements IUnitProcessingService 
{
	@Autowired
	private UnitProcessingMapper unitProcessingMapper;

	/**
     * 查询单元加工记录信息
     * 
     * @param id 单元加工记录ID
     * @return 单元加工记录信息
     */
    @Override
	public UnitProcessing selectUnitProcessingById(Integer id)
	{
	    return unitProcessingMapper.selectUnitProcessingById(id);
	}
	
	/**
     * 查询单元加工记录列表
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 单元加工记录集合
     */
	@Override
	public List<UnitProcessing> selectUnitProcessingList(UnitProcessing unitProcessing)
	{
	    return unitProcessingMapper.selectUnitProcessingList(unitProcessing);
	}
	
    /**
     * 新增单元加工记录
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 结果
     */
	@Override
	public int insertUnitProcessing(UnitProcessing unitProcessing)
	{
	    return unitProcessingMapper.insertUnitProcessing(unitProcessing);
	}
	
	/**
     * 修改单元加工记录
     * 
     * @param unitProcessing 单元加工记录信息
     * @return 结果
     */
	@Override
	public int updateUnitProcessing(UnitProcessing unitProcessing)
	{
	    return unitProcessingMapper.updateUnitProcessing(unitProcessing);
	}

	/**
     * 删除单元加工记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteUnitProcessingByIds(String ids)
	{
		return unitProcessingMapper.deleteUnitProcessingByIds(Convert.toStrArray(ids));
	}
	
}
