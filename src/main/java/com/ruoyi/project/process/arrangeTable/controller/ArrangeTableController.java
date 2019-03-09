package com.ruoyi.project.process.arrangeTable.controller;

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
import com.ruoyi.project.process.arrangeTable.domain.ArrangeTable;
import com.ruoyi.project.process.arrangeTable.service.IArrangeTableService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 派工记录 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/arrangeTable")
public class ArrangeTableController extends BaseController
{
    private String prefix = "process/arrangeTable";
	
	@Autowired
	private IArrangeTableService arrangeTableService;
	
	@RequiresPermissions("process:arrangeTable:view")
	@GetMapping()
	public String arrangeTable()
	{
	    return prefix + "/arrangeTable";
	}
	
	/**
	 * 查询派工记录列表
	 */
	@RequiresPermissions("process:arrangeTable:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ArrangeTable arrangeTable)
	{
		startPage();
        List<ArrangeTable> list = arrangeTableService.selectArrangeTableList(arrangeTable);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出派工记录列表
	 */
	@RequiresPermissions("process:arrangeTable:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ArrangeTable arrangeTable)
    {
    	List<ArrangeTable> list = arrangeTableService.selectArrangeTableList(arrangeTable);
        ExcelUtil<ArrangeTable> util = new ExcelUtil<ArrangeTable>(ArrangeTable.class);
        return util.exportExcel(list, "arrangeTable");
    }
	
	/**
	 * 新增派工记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存派工记录
	 */
	@RequiresPermissions("process:arrangeTable:add")
	@Log(title = "派工记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ArrangeTable arrangeTable)
	{		
		return toAjax(arrangeTableService.insertArrangeTable(arrangeTable));
	}

	/**
	 * 修改派工记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ArrangeTable arrangeTable = arrangeTableService.selectArrangeTableById(id);
		mmap.put("arrangeTable", arrangeTable);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存派工记录
	 */
	@RequiresPermissions("process:arrangeTable:edit")
	@Log(title = "派工记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ArrangeTable arrangeTable)
	{		
		return toAjax(arrangeTableService.updateArrangeTable(arrangeTable));
	}
	
	/**
	 * 删除派工记录
	 */
	@RequiresPermissions("process:arrangeTable:remove")
	@Log(title = "派工记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(arrangeTableService.deleteArrangeTableByIds(ids));
	}
	
}
