package com.ruoyi.project.pipe.batch.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.batch.mapper.BatchMapper;
import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.batch.service.IBatchService;
import com.ruoyi.common.support.Convert;

/**
 * 加工批次 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class BatchServiceImpl implements IBatchService 
{
	@Autowired
	private BatchMapper batchMapper;

	/**
     * 查询加工批次信息
     * 
     * @param id 加工批次ID
     * @return 加工批次信息
     */
    @Override
	public Batch selectBatchById(Integer id)
	{
	    return batchMapper.selectBatchById(id);
	}
	
	/**
     * 查询加工批次列表
     * 
     * @param batch 加工批次信息
     * @return 加工批次集合
     */
	@Override
	public List<Batch> selectBatchList(Batch batch)
	{
	    return batchMapper.selectBatchList(batch);
	}
	
    /**
     * 新增加工批次
     * 
     * @param batch 加工批次信息
     * @return 结果
     */
	@Override
	public int insertBatch(Batch batch)
	{
	    return batchMapper.insertBatch(batch);
	}
	
	/**
     * 修改加工批次
     * 
     * @param batch 加工批次信息
     * @return 结果
     */
	@Override
	public int updateBatch(Batch batch)
	{
	    return batchMapper.updateBatch(batch);
	}

	/**
     * 删除加工批次对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBatchByIds(String ids)
	{
		return batchMapper.deleteBatchByIds(Convert.toStrArray(ids));
	}
	
}
