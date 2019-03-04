package com.ruoyi.project.pipe.pipCutting.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipCutting.mapper.PipCuttingMapper;
import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipCutting.service.IPipCuttingService;
import com.ruoyi.common.support.Convert;

/**
 * 下料 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class PipCuttingServiceImpl implements IPipCuttingService 
{
	@Autowired
	private PipCuttingMapper pipCuttingMapper;

	/**
     * 查询下料信息
     * 
     * @param oldId 下料ID
     * @return 下料信息
     */
    @Override
	public PipCutting selectPipCuttingById(Integer oldId)
	{
	    return pipCuttingMapper.selectPipCuttingById(oldId);
	}
	
	/**
     * 查询下料列表
     * 
     * @param pipCutting 下料信息
     * @return 下料集合
     */
	@Override
	public List<PipCutting> selectPipCuttingList(PipCutting pipCutting)
	{
	    return pipCuttingMapper.selectPipCuttingList(pipCutting);
	}
	
    /**
     * 新增下料
     * 
     * @param pipCutting 下料信息
     * @return 结果
     */
	@Override
	public int insertPipCutting(PipCutting pipCutting)
	{
	    return pipCuttingMapper.insertPipCutting(pipCutting);
	}
	
	/**
     * 修改下料
     * 
     * @param pipCutting 下料信息
     * @return 结果
     */
	@Override
	public int updatePipCutting(PipCutting pipCutting)
	{
	    return pipCuttingMapper.updatePipCutting(pipCutting);
	}

	/**
     * 删除下料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipCuttingByIds(String ids)
	{
		return pipCuttingMapper.deletePipCuttingByIds(Convert.toStrArray(ids));
	}
	
}
