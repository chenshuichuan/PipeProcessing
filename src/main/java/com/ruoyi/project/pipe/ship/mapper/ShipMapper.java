package com.ruoyi.project.pipe.ship.mapper;

import com.ruoyi.project.pipe.ship.domain.Ship;
import java.util.List;	

/**
 * 号船 数据层
 * 
 * @author ricardo
 * @date 2019-03-04
 */
public interface ShipMapper 
{
	/**
     * 查询号船信息
     * 
     * @param id 号船ID
     * @return 号船信息
     */
	public Ship selectShipById(Integer id);
	
	/**
     * 查询号船列表
     * 
     * @param ship 号船信息
     * @return 号船集合
     */
	public List<Ship> selectShipList(Ship ship);
	
	/**
     * 新增号船
     * 
     * @param ship 号船信息
     * @return 结果
     */
	public int insertShip(Ship ship);
	
	/**
     * 修改号船
     * 
     * @param ship 号船信息
     * @return 结果
     */
	public int updateShip(Ship ship);
	
	/**
     * 删除号船
     * 
     * @param id 号船ID
     * @return 结果
     */
	public int deleteShipById(Integer id);
	
	/**
     * 批量删除号船
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteShipByIds(String[] ids);
	
}