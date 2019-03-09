package com.ruoyi.project.process.order.service;

import com.ruoyi.project.process.order.domain.Order;
import java.util.List;

/**
 * 加工顺序 服务层
 * 
 * @author ricardo
 * @date 2019-03-08
 */
public interface IOrderService 
{
	/**
     * 查询加工顺序信息
     * 
     * @param id 加工顺序ID
     * @return 加工顺序信息
     */
	public Order selectOrderById(Integer id);
	
	/**
     * 查询加工顺序列表
     * 
     * @param order 加工顺序信息
     * @return 加工顺序集合
     */
	public List<Order> selectOrderList(Order order);
	
	/**
     * 新增加工顺序
     * 
     * @param order 加工顺序信息
     * @return 结果
     */
	public int insertOrder(Order order);
	
	/**
     * 修改加工顺序
     * 
     * @param order 加工顺序信息
     * @return 结果
     */
	public int updateOrder(Order order);
		
	/**
     * 删除加工顺序信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteOrderByIds(String ids);
	
}
