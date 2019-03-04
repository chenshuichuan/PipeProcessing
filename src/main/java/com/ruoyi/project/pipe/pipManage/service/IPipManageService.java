package com.ruoyi.project.pipe.pipManage.service;

import com.ruoyi.project.pipe.pipManage.domain.PipManage;
import java.util.List;

/**
 * 派工单 服务层
 * 
 * @author ricardo
 * @date 2019-03-04
 */
public interface IPipManageService 
{
	/**
     * 查询派工单信息
     * 
     * @param oldId 派工单ID
     * @return 派工单信息
     */
	public PipManage selectPipManageById(Integer oldId);
	
	/**
     * 查询派工单列表
     * 
     * @param pipManage 派工单信息
     * @return 派工单集合
     */
	public List<PipManage> selectPipManageList(PipManage pipManage);
	
	/**
     * 新增派工单
     * 
     * @param pipManage 派工单信息
     * @return 结果
     */
	public int insertPipManage(PipManage pipManage);
	
	/**
     * 修改派工单
     * 
     * @param pipManage 派工单信息
     * @return 结果
     */
	public int updatePipManage(PipManage pipManage);
		
	/**
     * 删除派工单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePipManageByIds(String ids);
	
}
