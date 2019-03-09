package com.ruoyi.project.process.pipeProcessing.service;

import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import java.util.List;

/**
 * 管件加工 服务层
 * 
 * @author ricardo
 * @date 2019-03-08
 */
public interface IPipeProcessingService 
{
	/**
     * 查询管件加工信息
     * 
     * @param id 管件加工ID
     * @return 管件加工信息
     */
	public PipeProcessing selectPipeProcessingById(Integer id);
	
	/**
     * 查询管件加工列表
     * 
     * @param pipeProcessing 管件加工信息
     * @return 管件加工集合
     */
	public List<PipeProcessing> selectPipeProcessingList(PipeProcessing pipeProcessing);
	
	/**
     * 新增管件加工
     * 
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
	public int insertPipeProcessing(PipeProcessing pipeProcessing);
	
	/**
     * 修改管件加工
     * 
     * @param pipeProcessing 管件加工信息
     * @return 结果
     */
	public int updatePipeProcessing(PipeProcessing pipeProcessing);
		
	/**
     * 删除管件加工信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePipeProcessingByIds(String ids);
	
}
