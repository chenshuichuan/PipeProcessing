package com.ruoyi.project.pipe.pipStore.service;

import com.ruoyi.project.pipe.pipStore.domain.PipStore;
import java.util.List;

/**
 * 管子存放 服务层
 * 
 * @author ricardo
 * @date 2019-03-04
 */
public interface IPipStoreService 
{
	/**
     * 查询管子存放信息
     * 
     * @param oldId 管子存放ID
     * @return 管子存放信息
     */
	public PipStore selectPipStoreById(Integer oldId);
	
	/**
     * 查询管子存放列表
     * 
     * @param pipStore 管子存放信息
     * @return 管子存放集合
     */
	public List<PipStore> selectPipStoreList(PipStore pipStore);
	
	/**
     * 新增管子存放
     * 
     * @param pipStore 管子存放信息
     * @return 结果
     */
	public int insertPipStore(PipStore pipStore);
	
	/**
     * 修改管子存放
     * 
     * @param pipStore 管子存放信息
     * @return 结果
     */
	public int updatePipStore(PipStore pipStore);
		
	/**
     * 删除管子存放信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePipStoreByIds(String ids);
	
}
