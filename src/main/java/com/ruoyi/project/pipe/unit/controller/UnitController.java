package com.ruoyi.project.pipe.unit.controller;

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
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加工单元 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/unit")
public class UnitController extends BaseController
{
    private String prefix = "pipe/unit";
	
	@Autowired
	private IUnitService unitService;
	
	@RequiresPermissions("pipe:unit:view")
	@GetMapping()
	public String unit()
	{
	    return prefix + "/unit";
	}
	
	/**
	 * 查询加工单元列表
	 */
	@RequiresPermissions("pipe:unit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Unit unit)
	{
		startPage();
        List<Unit> list = unitService.selectUnitList(unit);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加工单元列表
	 */
	@RequiresPermissions("pipe:unit:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Unit unit)
    {
    	List<Unit> list = unitService.selectUnitList(unit);
        ExcelUtil<Unit> util = new ExcelUtil<Unit>(Unit.class);
        return util.exportExcel(list, "unit");
    }
	
	/**
	 * 新增加工单元
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存加工单元
	 */
	@RequiresPermissions("pipe:unit:add")
	@Log(title = "加工单元", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Unit unit)
	{		
		return toAjax(unitService.insertUnit(unit));
	}

	/**
	 * 修改加工单元
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Unit unit = unitService.selectUnitById(id);
		mmap.put("unit", unit);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存加工单元
	 */
	@RequiresPermissions("pipe:unit:edit")
	@Log(title = "加工单元", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Unit unit)
	{		
		return toAjax(unitService.updateUnit(unit));
	}
	
	/**
	 * 删除加工单元
	 */
	@RequiresPermissions("pipe:unit:remove")
	@Log(title = "加工单元", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(unitService.deleteUnitByIds(ids));
	}
	
}
