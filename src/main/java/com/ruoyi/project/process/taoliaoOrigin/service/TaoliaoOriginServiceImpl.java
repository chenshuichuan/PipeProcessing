package com.ruoyi.project.process.taoliaoOrigin.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.taoliaoOrigin.mapper.TaoliaoOriginMapper;
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoOrigin.service.ITaoliaoOriginService;
import com.ruoyi.common.support.Convert;

/**
 * 套料原材料 服务层实现
 * 
 * @author ricardo
 * @date 2019-04-19
 */
@Service
public class TaoliaoOriginServiceImpl implements ITaoliaoOriginService 
{
	@Autowired
	private TaoliaoOriginMapper taoliaoOriginMapper;

	/**
     * 查询套料原材料信息
     * 
     * @param id 套料原材料ID
     * @return 套料原材料信息
     */
    @Override
	public TaoliaoOrigin selectTaoliaoOriginById(Integer id)
	{
	    return taoliaoOriginMapper.selectTaoliaoOriginById(id);
	}
	
	/**
     * 查询套料原材料列表
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 套料原材料集合
     */
	@Override
	public List<TaoliaoOrigin> selectTaoliaoOriginList(TaoliaoOrigin taoliaoOrigin)
	{
	    return taoliaoOriginMapper.selectTaoliaoOriginList(taoliaoOrigin);
	}
	
    /**
     * 新增套料原材料
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 结果
     */
	@Override
	public int insertTaoliaoOrigin(TaoliaoOrigin taoliaoOrigin)
	{
	    return taoliaoOriginMapper.insertTaoliaoOrigin(taoliaoOrigin);
	}
	
	/**
     * 修改套料原材料
     * 
     * @param taoliaoOrigin 套料原材料信息
     * @return 结果
     */
	@Override
	public int updateTaoliaoOrigin(TaoliaoOrigin taoliaoOrigin)
	{
	    return taoliaoOriginMapper.updateTaoliaoOrigin(taoliaoOrigin);
	}

	/**
     * 删除套料原材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTaoliaoOriginByIds(String ids)
	{
		return taoliaoOriginMapper.deleteTaoliaoOriginByIds(Convert.toStrArray(ids));
	}
	
}
