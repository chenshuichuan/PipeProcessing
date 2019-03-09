package com.ruoyi.project.process.arrangeTable.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.arrangeTable.mapper.ArrangeTableMapper;
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.service.IArrangeTableService;
import com.ruoyi.common.support.Convert;

/**
 * 派工记录 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class ArrangeTableServiceImpl implements IArrangeTableService 
{
	@Autowired
	private ArrangeTableMapper arrangeTableMapper;

	/**
     * 查询派工记录信息
     * 
     * @param id 派工记录ID
     * @return 派工记录信息
     */
    @Override
	public ArrangeTable selectArrangeTableById(Integer id)
	{
	    return arrangeTableMapper.selectArrangeTableById(id);
	}
	
	/**
     * 查询派工记录列表
     * 
     * @param arrangeTable 派工记录信息
     * @return 派工记录集合
     */
	@Override
	public List<ArrangeTable> selectArrangeTableList(ArrangeTable arrangeTable)
	{
	    return arrangeTableMapper.selectArrangeTableList(arrangeTable);
	}
	
    /**
     * 新增派工记录
     * 
     * @param arrangeTable 派工记录信息
     * @return 结果
     */
	@Override
	public int insertArrangeTable(ArrangeTable arrangeTable)
	{
	    return arrangeTableMapper.insertArrangeTable(arrangeTable);
	}
	
	/**
     * 修改派工记录
     * 
     * @param arrangeTable 派工记录信息
     * @return 结果
     */
	@Override
	public int updateArrangeTable(ArrangeTable arrangeTable)
	{
	    return arrangeTableMapper.updateArrangeTable(arrangeTable);
	}

	/**
     * 删除派工记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteArrangeTableByIds(String ids)
	{
		return arrangeTableMapper.deleteArrangeTableByIds(Convert.toStrArray(ids));
	}
	
}
