package com.ruoyi.project.pipe.ship.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.pipe.ship.mapper.ShipMapper;
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.common.support.Convert;

/**
 * 号船 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Service
public class ShipServiceImpl implements IShipService 
{
	@Autowired
	private ShipMapper shipMapper;

	/**
     * 查询号船信息
     * 
     * @param id 号船ID
     * @return 号船信息
     */
    @Override
	public Ship selectShipById(Integer id)
	{
	    return shipMapper.selectShipById(id);
	}
	
	/**
     * 查询号船列表
     * 
     * @param ship 号船信息
     * @return 号船集合
     */
	@Override
	public List<Ship> selectShipList(Ship ship)
	{
	    return shipMapper.selectShipList(ship);
	}
	
    /**
     * 新增号船
     * 
     * @param ship 号船信息
     * @return 结果
     */
	@Override
	public int insertShip(Ship ship)
	{
	    return shipMapper.insertShip(ship);
	}
	
	/**
     * 修改号船
     * 
     * @param ship 号船信息
     * @return 结果
     */
	@Override
	public int updateShip(Ship ship)
	{
	    return shipMapper.updateShip(ship);
	}

	/**
     * 删除号船对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteShipByIds(String ids)
	{
		return shipMapper.deleteShipByIds(Convert.toStrArray(ids));
	}
	
}
