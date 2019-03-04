package com.ruoyi.project.pipe.pipe.mapper;

import com.ruoyi.project.pipe.pipe.domain.Pipe;
import java.util.List;	

/**
 * 管件 数据层
 * 
 * @author ricardo
 * @date 2019-03-04
 */
public interface PipeMapper 
{
	/**
     * 查询管件信息
     * 
     * @param id 管件ID
     * @return 管件信息
     */
	public Pipe selectPipeById(Integer id);
	
	/**
     * 查询管件列表
     * 
     * @param pipe 管件信息
     * @return 管件集合
     */
	public List<Pipe> selectPipeList(Pipe pipe);
	
	/**
     * 新增管件
     * 
     * @param pipe 管件信息
     * @return 结果
     */
	public int insertPipe(Pipe pipe);
	
	/**
     * 修改管件
     * 
     * @param pipe 管件信息
     * @return 结果
     */
	public int updatePipe(Pipe pipe);
	
	/**
     * 删除管件
     * 
     * @param id 管件ID
     * @return 结果
     */
	public int deletePipeById(Integer id);
	
	/**
     * 批量删除管件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePipeByIds(String[] ids);
	
}