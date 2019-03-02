package com.ruoyi.project.pipe.cutPlan.controller;

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
import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加工计划，不包含下料计划，下料计划请查看pipe_cut_plan 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/admin/pipe/cutPlan")
public class CutPlanController extends BaseController
{
    private String prefix = "pipe/cutPlan";
	
	@Autowired
	private ICutPlanService cutPlanService;
	
	@RequiresPermissions("pipe:cutPlan:view")
	@GetMapping()
	public String cutPlan()
	{
	    return prefix + "/cutPlan";
	}
	
	/**
	 * 查询加工计划，不包含下料计划，下料计划请查看pipe_cut_plan列表
	 */
	@RequiresPermissions("pipe:cutPlan:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CutPlan cutPlan)
	{
		startPage();
        List<CutPlan> list = cutPlanService.selectCutPlanList(cutPlan);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加工计划，不包含下料计划，下料计划请查看pipe_cut_plan列表
	 */
	@RequiresPermissions("pipe:cutPlan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CutPlan cutPlan)
    {
    	List<CutPlan> list = cutPlanService.selectCutPlanList(cutPlan);
        ExcelUtil<CutPlan> util = new ExcelUtil<CutPlan>(CutPlan.class);
        return util.exportExcel(list, "cutPlan");
    }
	
	/**
	 * 新增加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
	 */
	@RequiresPermissions("pipe:cutPlan:add")
	@Log(title = "加工计划，不包含下料计划，下料计划请查看pipe_cut_plan", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CutPlan cutPlan)
	{		
		return toAjax(cutPlanService.insertCutPlan(cutPlan));
	}

	/**
	 * 修改加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		CutPlan cutPlan = cutPlanService.selectCutPlanById(id);
		mmap.put("cutPlan", cutPlan);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
	 */
	@RequiresPermissions("pipe:cutPlan:edit")
	@Log(title = "加工计划，不包含下料计划，下料计划请查看pipe_cut_plan", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CutPlan cutPlan)
	{		
		return toAjax(cutPlanService.updateCutPlan(cutPlan));
	}
	
	/**
	 * 删除加工计划，不包含下料计划，下料计划请查看pipe_cut_plan
	 */
	@RequiresPermissions("pipe:cutPlan:remove")
	@Log(title = "加工计划，不包含下料计划，下料计划请查看pipe_cut_plan", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(cutPlanService.deleteCutPlanByIds(ids));
	}
	
}
