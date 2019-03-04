package com.ruoyi.project.pipe.pipManage.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipManage.mapper.PipManageMapper;
import com.ruoyi.project.pipe.pipManage.domain.PipManage;
import com.ruoyi.project.pipe.pipManage.service.IPipManageService;
import com.ruoyi.common.support.Convert;

/**
 * 派工单 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class PipManageServiceImpl implements IPipManageService 
{
	@Autowired
	private PipManageMapper pipManageMapper;

	/**
     * 查询派工单信息
     * 
     * @param oldId 派工单ID
     * @return 派工单信息
     */
    @Override
	public PipManage selectPipManageById(Integer oldId)
	{
	    return pipManageMapper.selectPipManageById(oldId);
	}
	
	/**
     * 查询派工单列表
     * 
     * @param pipManage 派工单信息
     * @return 派工单集合
     */
	@Override
	public List<PipManage> selectPipManageList(PipManage pipManage)
	{
	    return pipManageMapper.selectPipManageList(pipManage);
	}
	
    /**
     * 新增派工单
     * 
     * @param pipManage 派工单信息
     * @return 结果
     */
	@Override
	public int insertPipManage(PipManage pipManage)
	{
	    return pipManageMapper.insertPipManage(pipManage);
	}
	
	/**
     * 修改派工单
     * 
     * @param pipManage 派工单信息
     * @return 结果
     */
	@Override
	public int updatePipManage(PipManage pipManage)
	{
	    return pipManageMapper.updatePipManage(pipManage);
	}

	/**
     * 删除派工单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipManageByIds(String ids)
	{
		return pipManageMapper.deletePipManageByIds(Convert.toStrArray(ids));
	}
	
}
