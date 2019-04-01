package com.ruoyi.project.process.middleStatus.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.middleStatus.mapper.MiddleStatusMapper;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.IMiddleStatusService;
import com.ruoyi.common.support.Convert;

/**
 * 中间数据读取程序状态 服务层实现
 * 
 * @author ricardo
 * @date 2019-04-01
 */
@Service
public class MiddleStatusServiceImpl implements IMiddleStatusService 
{
	@Autowired
	private MiddleStatusMapper middleStatusMapper;

	/**
     * 查询中间数据读取程序状态信息
     * 
     * @param id 中间数据读取程序状态ID
     * @return 中间数据读取程序状态信息
     */
    @Override
	public MiddleStatus selectMiddleStatusById(Integer id)
	{
	    return middleStatusMapper.selectMiddleStatusById(id);
	}
	
	/**
     * 查询中间数据读取程序状态列表
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 中间数据读取程序状态集合
     */
	@Override
	public List<MiddleStatus> selectMiddleStatusList(MiddleStatus middleStatus)
	{
	    return middleStatusMapper.selectMiddleStatusList(middleStatus);
	}
	
    /**
     * 新增中间数据读取程序状态
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 结果
     */
	@Override
	public int insertMiddleStatus(MiddleStatus middleStatus)
	{
	    return middleStatusMapper.insertMiddleStatus(middleStatus);
	}
	
	/**
     * 修改中间数据读取程序状态
     * 
     * @param middleStatus 中间数据读取程序状态信息
     * @return 结果
     */
	@Override
	public int updateMiddleStatus(MiddleStatus middleStatus)
	{
	    return middleStatusMapper.updateMiddleStatus(middleStatus);
	}

	/**
     * 删除中间数据读取程序状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMiddleStatusByIds(String ids)
	{
		return middleStatusMapper.deleteMiddleStatusByIds(Convert.toStrArray(ids));
	}
	
}
