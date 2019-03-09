package com.ruoyi.project.process.taoliao.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.taoliao.mapper.TaoliaoMapper;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import com.ruoyi.common.support.Convert;

/**
 * 套料管材 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class TaoliaoServiceImpl implements ITaoliaoService 
{
	@Autowired
	private TaoliaoMapper taoliaoMapper;

	/**
     * 查询套料管材信息
     * 
     * @param id 套料管材ID
     * @return 套料管材信息
     */
    @Override
	public Taoliao selectTaoliaoById(Integer id)
	{
	    return taoliaoMapper.selectTaoliaoById(id);
	}
	
	/**
     * 查询套料管材列表
     * 
     * @param taoliao 套料管材信息
     * @return 套料管材集合
     */
	@Override
	public List<Taoliao> selectTaoliaoList(Taoliao taoliao)
	{
	    return taoliaoMapper.selectTaoliaoList(taoliao);
	}
	
    /**
     * 新增套料管材
     * 
     * @param taoliao 套料管材信息
     * @return 结果
     */
	@Override
	public int insertTaoliao(Taoliao taoliao)
	{
	    return taoliaoMapper.insertTaoliao(taoliao);
	}
	
	/**
     * 修改套料管材
     * 
     * @param taoliao 套料管材信息
     * @return 结果
     */
	@Override
	public int updateTaoliao(Taoliao taoliao)
	{
	    return taoliaoMapper.updateTaoliao(taoliao);
	}

	/**
     * 删除套料管材对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTaoliaoByIds(String ids)
	{
		return taoliaoMapper.deleteTaoliaoByIds(Convert.toStrArray(ids));
	}
	
}
