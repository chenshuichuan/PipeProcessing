package com.ruoyi.project.pipe.workPipe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.workPipe.mapper.WorkPipeMapper;
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.pipe.workPipe.service.IWorkPipeService;
import com.ruoyi.common.support.Convert;

/**
 * 管件 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class WorkPipeServiceImpl implements IWorkPipeService 
{
	@Autowired
	private WorkPipeMapper workPipeMapper;

	/**
     * 查询管件信息
     * 
     * @param id 管件ID
     * @return 管件信息
     */
    @Override
	public WorkPipe selectWorkPipeById(Integer id)
	{
	    return workPipeMapper.selectWorkPipeById(id);
	}
	
	/**
     * 查询管件列表
     * 
     * @param workPipe 管件信息
     * @return 管件集合
     */
	@Override
	public List<WorkPipe> selectWorkPipeList(WorkPipe workPipe)
	{
	    return workPipeMapper.selectWorkPipeList(workPipe);
	}
	
    /**
     * 新增管件
     * 
     * @param workPipe 管件信息
     * @return 结果
     */
	@Override
	public int insertWorkPipe(WorkPipe workPipe)
	{
	    return workPipeMapper.insertWorkPipe(workPipe);
	}
	
	/**
     * 修改管件
     * 
     * @param workPipe 管件信息
     * @return 结果
     */
	@Override
	public int updateWorkPipe(WorkPipe workPipe)
	{
	    return workPipeMapper.updateWorkPipe(workPipe);
	}

	/**
     * 删除管件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWorkPipeByIds(String ids)
	{
		return workPipeMapper.deleteWorkPipeByIds(Convert.toStrArray(ids));
	}
	
}
