package com.ruoyi.project.process.taoliaoOrigin.controller;

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
import com.ruoyi.project.process.taoliaoOrigin.domain.TaoliaoOrigin;
import com.ruoyi.project.process.taoliaoOrigin.service.ITaoliaoOriginService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 套料原材料 信息操作处理
 * 
 * @author ricardo
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/admin/process/taoliaoOrigin")
public class TaoliaoOriginController extends BaseController
{
    private String prefix = "process/taoliaoOrigin";
	
	@Autowired
	private ITaoliaoOriginService taoliaoOriginService;
	
	@RequiresPermissions("process:taoliaoOrigin:view")
	@GetMapping()
	public String taoliaoOrigin()
	{
	    return prefix + "/taoliaoOrigin";
	}
	
	/**
	 * 查询套料原材料列表
	 */
	@RequiresPermissions("process:taoliaoOrigin:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TaoliaoOrigin taoliaoOrigin)
	{
		startPage();
        List<TaoliaoOrigin> list = taoliaoOriginService.selectTaoliaoOriginList(taoliaoOrigin);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出套料原材料列表
	 */
	@RequiresPermissions("process:taoliaoOrigin:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaoliaoOrigin taoliaoOrigin)
    {
    	List<TaoliaoOrigin> list = taoliaoOriginService.selectTaoliaoOriginList(taoliaoOrigin);
        ExcelUtil<TaoliaoOrigin> util = new ExcelUtil<TaoliaoOrigin>(TaoliaoOrigin.class);
        return util.exportExcel(list, "taoliaoOrigin");
    }
	
	/**
	 * 新增套料原材料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存套料原材料
	 */
	@RequiresPermissions("process:taoliaoOrigin:add")
	@Log(title = "套料原材料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TaoliaoOrigin taoliaoOrigin)
	{		
		return toAjax(taoliaoOriginService.insertTaoliaoOrigin(taoliaoOrigin));
	}

	/**
	 * 修改套料原材料
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		TaoliaoOrigin taoliaoOrigin = taoliaoOriginService.selectTaoliaoOriginById(id);
		mmap.put("taoliaoOrigin", taoliaoOrigin);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存套料原材料
	 */
	@RequiresPermissions("process:taoliaoOrigin:edit")
	@Log(title = "套料原材料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TaoliaoOrigin taoliaoOrigin)
	{		
		return toAjax(taoliaoOriginService.updateTaoliaoOrigin(taoliaoOrigin));
	}
	
	/**
	 * 删除套料原材料
	 */
	@RequiresPermissions("process:taoliaoOrigin:remove")
	@Log(title = "套料原材料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(taoliaoOriginService.deleteTaoliaoOriginByIds(ids));
	}
	
}
