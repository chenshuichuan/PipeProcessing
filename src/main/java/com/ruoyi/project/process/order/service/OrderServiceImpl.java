package com.ruoyi.project.process.order.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.process.order.mapper.OrderMapper;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.common.support.Convert;

/**
 * 加工顺序 服务层实现
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Service
public class OrderServiceImpl implements IOrderService 
{
	@Autowired
	private OrderMapper orderMapper;

	/**
     * 查询加工顺序信息
     * 
     * @param id 加工顺序ID
     * @return 加工顺序信息
     */
    @Override
	public Order selectOrderById(Integer id)
	{
	    return orderMapper.selectOrderById(id);
	}
	
	/**
     * 查询加工顺序列表
     * 
     * @param order 加工顺序信息
     * @return 加工顺序集合
     */
	@Override
	public List<Order> selectOrderList(Order order)
	{
	    return orderMapper.selectOrderList(order);
	}
	
    /**
     * 新增加工顺序
     * 
     * @param order 加工顺序信息
     * @return 结果
     */
	@Override
	public int insertOrder(Order order)
	{
	    return orderMapper.insertOrder(order);
	}
	
	/**
     * 修改加工顺序
     * 
     * @param order 加工顺序信息
     * @return 结果
     */
	@Override
	public int updateOrder(Order order)
	{
	    return orderMapper.updateOrder(order);
	}

	/**
     * 删除加工顺序对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteOrderByIds(String ids)
	{
		return orderMapper.deleteOrderByIds(Convert.toStrArray(ids));
	}
	
}
