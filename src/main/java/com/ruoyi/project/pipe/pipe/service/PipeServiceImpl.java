package com.ruoyi.project.pipe.pipe.service;

import java.util.List;

import com.ruoyi.project.process.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipe.mapper.PipeMapper;
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.IPipeService;
import com.ruoyi.common.support.Convert;

/**
 * 管件 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class PipeServiceImpl implements IPipeService 
{
	@Autowired
	private PipeMapper pipeMapper;

	/**
     * 查询管件信息
     * 
     * @param id 管件ID
     * @return 管件信息
     */
    @Override
	public Pipe selectPipeById(Integer id)
	{
	    return pipeMapper.selectPipeById(id);
	}
	
	/**
     * 查询管件列表
     * 
     * @param pipe 管件信息
     * @return 管件集合
     */
	@Override
	public List<Pipe> selectPipeList(Pipe pipe)
	{
	    return pipeMapper.selectPipeList(pipe);
	}
	
    /**
     * 新增管件
     * 
     * @param pipe 管件信息
     * @return 结果
     */
	@Override
	public int insertPipe(Pipe pipe)
	{
	    return pipeMapper.insertPipe(pipe);
	}
	
	/**
     * 修改管件
     * 
     * @param pipe 管件信息
     * @return 结果
     */
	@Override
	public int updatePipe(Pipe pipe)
	{
	    return pipeMapper.updatePipe(pipe);
	}

	/**
     * 删除管件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipeByIds(String ids)
	{
		return pipeMapper.deletePipeByIds(Convert.toStrArray(ids));
	}



}
