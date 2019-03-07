package com.ruoyi.project.pipe.pipPipe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipPipe.mapper.PipPipeMapper;
import com.ruoyi.project.pipe.pipPipe.domain.PipPipe;
import com.ruoyi.project.pipe.pipPipe.service.IPipPipeService;
import com.ruoyi.common.support.Convert;

/**
 * 中间数据库Pipe 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-06
 */
@Service
public class PipPipeServiceImpl implements IPipPipeService 
{
	@Autowired
	private PipPipeMapper pipPipeMapper;

	/**
     * 查询中间数据库Pipe信息
     * 
     * @param id 中间数据库PipeID
     * @return 中间数据库Pipe信息
     */
    @Override
	public PipPipe selectPipPipeById(Integer id)
	{
	    return pipPipeMapper.selectPipPipeById(id);
	}
	
	/**
     * 查询中间数据库Pipe列表
     * 
     * @param pipPipe 中间数据库Pipe信息
     * @return 中间数据库Pipe集合
     */
	@Override
	public List<PipPipe> selectPipPipeList(PipPipe pipPipe)
	{
	    return pipPipeMapper.selectPipPipeList(pipPipe);
	}
	
    /**
     * 新增中间数据库Pipe
     * 
     * @param pipPipe 中间数据库Pipe信息
     * @return 结果
     */
	@Override
	public int insertPipPipe(PipPipe pipPipe)
	{
	    return pipPipeMapper.insertPipPipe(pipPipe);
	}
	
	/**
     * 修改中间数据库Pipe
     * 
     * @param pipPipe 中间数据库Pipe信息
     * @return 结果
     */
	@Override
	public int updatePipPipe(PipPipe pipPipe)
	{
	    return pipPipeMapper.updatePipPipe(pipPipe);
	}

	/**
     * 删除中间数据库Pipe对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipPipeByIds(String ids)
	{
		return pipPipeMapper.deletePipPipeByIds(Convert.toStrArray(ids));
	}
	
}
