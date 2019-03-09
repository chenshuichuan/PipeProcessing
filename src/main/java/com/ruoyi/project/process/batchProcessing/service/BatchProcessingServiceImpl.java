package com.ruoyi.project.process.batchProcessing.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.batchProcessing.mapper.BatchProcessingMapper;
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import com.ruoyi.project.process.batchProcessing.service.IBatchProcessingService;
import com.ruoyi.common.support.Convert;

/**
 * 批次加工 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class BatchProcessingServiceImpl implements IBatchProcessingService 
{
	@Autowired
	private BatchProcessingMapper batchProcessingMapper;

	/**
     * 查询批次加工信息
     * 
     * @param id 批次加工ID
     * @return 批次加工信息
     */
    @Override
	public BatchProcessing selectBatchProcessingById(Integer id)
	{
	    return batchProcessingMapper.selectBatchProcessingById(id);
	}
	
	/**
     * 查询批次加工列表
     * 
     * @param batchProcessing 批次加工信息
     * @return 批次加工集合
     */
	@Override
	public List<BatchProcessing> selectBatchProcessingList(BatchProcessing batchProcessing)
	{
	    return batchProcessingMapper.selectBatchProcessingList(batchProcessing);
	}
	
    /**
     * 新增批次加工
     * 
     * @param batchProcessing 批次加工信息
     * @return 结果
     */
	@Override
	public int insertBatchProcessing(BatchProcessing batchProcessing)
	{
	    return batchProcessingMapper.insertBatchProcessing(batchProcessing);
	}
	
	/**
     * 修改批次加工
     * 
     * @param batchProcessing 批次加工信息
     * @return 结果
     */
	@Override
	public int updateBatchProcessing(BatchProcessing batchProcessing)
	{
	    return batchProcessingMapper.updateBatchProcessing(batchProcessing);
	}

	/**
     * 删除批次加工对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBatchProcessingByIds(String ids)
	{
		return batchProcessingMapper.deleteBatchProcessingByIds(Convert.toStrArray(ids));
	}
	
}
