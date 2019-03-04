package com.ruoyi.project.pipe.pipStore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipStore.mapper.PipStoreMapper;
import com.ruoyi.project.pipe.pipStore.domain.PipStore;
import com.ruoyi.project.pipe.pipStore.service.IPipStoreService;
import com.ruoyi.common.support.Convert;

/**
 * 管子存放 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class PipStoreServiceImpl implements IPipStoreService 
{
	@Autowired
	private PipStoreMapper pipStoreMapper;

	/**
     * 查询管子存放信息
     * 
     * @param oldId 管子存放ID
     * @return 管子存放信息
     */
    @Override
	public PipStore selectPipStoreById(Integer oldId)
	{
	    return pipStoreMapper.selectPipStoreById(oldId);
	}
	
	/**
     * 查询管子存放列表
     * 
     * @param pipStore 管子存放信息
     * @return 管子存放集合
     */
	@Override
	public List<PipStore> selectPipStoreList(PipStore pipStore)
	{
	    return pipStoreMapper.selectPipStoreList(pipStore);
	}
	
    /**
     * 新增管子存放
     * 
     * @param pipStore 管子存放信息
     * @return 结果
     */
	@Override
	public int insertPipStore(PipStore pipStore)
	{
	    return pipStoreMapper.insertPipStore(pipStore);
	}
	
	/**
     * 修改管子存放
     * 
     * @param pipStore 管子存放信息
     * @return 结果
     */
	@Override
	public int updatePipStore(PipStore pipStore)
	{
	    return pipStoreMapper.updatePipStore(pipStore);
	}

	/**
     * 删除管子存放对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipStoreByIds(String ids)
	{
		return pipStoreMapper.deletePipStoreByIds(Convert.toStrArray(ids));
	}
	
}
