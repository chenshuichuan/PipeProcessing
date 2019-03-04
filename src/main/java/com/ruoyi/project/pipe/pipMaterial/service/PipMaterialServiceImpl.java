package com.ruoyi.project.pipe.pipMaterial.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.pipMaterial.mapper.PipMaterialMapper;
import com.ruoyi.project.pipe.pipMaterial.domain.PipMaterial;
import com.ruoyi.project.pipe.pipMaterial.service.IPipMaterialService;
import com.ruoyi.common.support.Convert;

/**
 * 管子材料 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class PipMaterialServiceImpl implements IPipMaterialService 
{
	@Autowired
	private PipMaterialMapper pipMaterialMapper;

	/**
     * 查询管子材料信息
     * 
     * @param oldId 管子材料ID
     * @return 管子材料信息
     */
    @Override
	public PipMaterial selectPipMaterialById(Integer oldId)
	{
	    return pipMaterialMapper.selectPipMaterialById(oldId);
	}
	
	/**
     * 查询管子材料列表
     * 
     * @param pipMaterial 管子材料信息
     * @return 管子材料集合
     */
	@Override
	public List<PipMaterial> selectPipMaterialList(PipMaterial pipMaterial)
	{
	    return pipMaterialMapper.selectPipMaterialList(pipMaterial);
	}
	
    /**
     * 新增管子材料
     * 
     * @param pipMaterial 管子材料信息
     * @return 结果
     */
	@Override
	public int insertPipMaterial(PipMaterial pipMaterial)
	{
	    return pipMaterialMapper.insertPipMaterial(pipMaterial);
	}
	
	/**
     * 修改管子材料
     * 
     * @param pipMaterial 管子材料信息
     * @return 结果
     */
	@Override
	public int updatePipMaterial(PipMaterial pipMaterial)
	{
	    return pipMaterialMapper.updatePipMaterial(pipMaterial);
	}

	/**
     * 删除管子材料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePipMaterialByIds(String ids)
	{
		return pipMaterialMapper.deletePipMaterialByIds(Convert.toStrArray(ids));
	}
	
}
