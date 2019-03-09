package com.ruoyi.project.process.batchProcessing.service;

import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import java.util.List;

/**
 * 批次加工 服务层
 * 
 * @author ricardo
 * @date 2019-03-08
 */
public interface IBatchProcessingService 
{
	/**
     * 查询批次加工信息
     * 
     * @param id 批次加工ID
     * @return 批次加工信息
     */
	public BatchProcessing selectBatchProcessingById(Integer id);
	
	/**
     * 查询批次加工列表
     * 
     * @param batchProcessing 批次加工信息
     * @return 批次加工集合
     */
	public List<BatchProcessing> selectBatchProcessingList(BatchProcessing batchProcessing);
	
	/**
     * 新增批次加工
     * 
     * @param batchProcessing 批次加工信息
     * @return 结果
     */
	public int insertBatchProcessing(BatchProcessing batchProcessing);
	
	/**
     * 修改批次加工
     * 
     * @param batchProcessing 批次加工信息
     * @return 结果
     */
	public int updateBatchProcessing(BatchProcessing batchProcessing);
		
	/**
     * 删除批次加工信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBatchProcessingByIds(String ids);
	
}
