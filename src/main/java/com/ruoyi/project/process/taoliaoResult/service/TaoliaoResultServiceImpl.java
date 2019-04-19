package com.ruoyi.project.process.taoliaoResult.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.taoliaoResult.mapper.TaoliaoResultMapper;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.service.ITaoliaoResultService;
import com.ruoyi.common.support.Convert;

/**
 * 管材套料结果 服务层实现
 * 
 * @author ricardo
 * @date 2019-04-19
 */
@Service
public class TaoliaoResultServiceImpl implements ITaoliaoResultService 
{
	@Autowired
	private TaoliaoResultMapper taoliaoResultMapper;

	/**
     * 查询管材套料结果信息
     * 
     * @param id 管材套料结果ID
     * @return 管材套料结果信息
     */
    @Override
	public TaoliaoResult selectTaoliaoResultById(Integer id)
	{
	    return taoliaoResultMapper.selectTaoliaoResultById(id);
	}
	
	/**
     * 查询管材套料结果列表
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 管材套料结果集合
     */
	@Override
	public List<TaoliaoResult> selectTaoliaoResultList(TaoliaoResult taoliaoResult)
	{
	    return taoliaoResultMapper.selectTaoliaoResultList(taoliaoResult);
	}
	
    /**
     * 新增管材套料结果
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 结果
     */
	@Override
	public int insertTaoliaoResult(TaoliaoResult taoliaoResult)
	{
	    return taoliaoResultMapper.insertTaoliaoResult(taoliaoResult);
	}
	
	/**
     * 修改管材套料结果
     * 
     * @param taoliaoResult 管材套料结果信息
     * @return 结果
     */
	@Override
	public int updateTaoliaoResult(TaoliaoResult taoliaoResult)
	{
	    return taoliaoResultMapper.updateTaoliaoResult(taoliaoResult);
	}

	/**
     * 删除管材套料结果对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTaoliaoResultByIds(String ids)
	{
		return taoliaoResultMapper.deleteTaoliaoResultByIds(Convert.toStrArray(ids));
	}
	
}
