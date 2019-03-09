package com.ruoyi.project.process.order.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加工顺序 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/order")
public class OrderController extends BaseController
{
    private String prefix = "process/order";
	
	@Autowired
	private IOrderService orderService;
	
	@RequiresPermissions("process:order:view")
	@GetMapping()
	public String order()
	{
	    return prefix + "/order";
	}
	
	/**
	 * 查询加工顺序列表
	 */
	@RequiresPermissions("process:order:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Order order)
	{
		startPage();
        List<Order> list = orderService.selectOrderList(order);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加工顺序列表
	 */
	@RequiresPermissions("process:order:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Order order)
    {
    	List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.exportExcel(list, "order");
    }
	
	/**
	 * 新增加工顺序
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存加工顺序
	 */
	@RequiresPermissions("process:order:add")
	@Log(title = "加工顺序", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Order order)
	{		
		return toAjax(orderService.insertOrder(order));
	}

	/**
	 * 修改加工顺序
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Order order = orderService.selectOrderById(id);
		mmap.put("order", order);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存加工顺序
	 */
	@RequiresPermissions("process:order:edit")
	@Log(title = "加工顺序", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Order order)
	{		
		return toAjax(orderService.updateOrder(order));
	}
	
	/**
	 * 删除加工顺序
	 */
	@RequiresPermissions("process:order:remove")
	@Log(title = "加工顺序", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(orderService.deleteOrderByIds(ids));
	}
	
}
