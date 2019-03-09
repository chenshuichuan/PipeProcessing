package com.ruoyi.project.process.pipeProcessing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.pipeProcessing.mapper.PipeProcessingMapper;
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.common.support.Convert;

/**
 * 管件加工 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class PipeProcessingServiceImpl implements IPipeProcessingService 
{
	@Autowired
	private PipeProcessingMapper pipeProcessingMapper;

	/**
     * 查询管件加工信息
     * 
     * @param id 管件加工ID
     * @return 管件加工信息
     */
    @Override
	public PipeProcessing selectPipeProcessingById(Integer id)
	{
	    return pipeProcessingMapper.selectPipeProcessingById(id);
	}
	
	/**
     * 查询管件加工列表
     * 
     * @param pipeProcessing 管件加工信息
     * @return 管件加工集合
     */
	@Override
	public List<PipeProcessing> selectPipeProcessingList(PipeProcessing pipeProcessing)
	{
	    return pipeProcessingMapper.selectPipeProcessingList(pipeProcessing);
	}
	
    /**
     * 新增管件加工
     * 
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
	@Override
	public int insertPipeProcessing(PipeProcessing pipeProcessing)
	{
	    return pipeProcessingMapper.insertPipeProcessing(pipeProcessing);
	}
	
	/**
     * 修改管件加工
     * 
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
	@Override
	public int updatePipeProcessing(PipeProcessing pipeProcessing)
	{
	    return pipeProcessingMapper.updatePipeProcessing(pipeProcessing);
	}

	/**
     * 删除管件加工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipeProcessingByIds(String ids)
	{
		return pipeProcessingMapper.deletePipeProcessingByIds(Convert.toStrArray(ids));
	}
	
}
