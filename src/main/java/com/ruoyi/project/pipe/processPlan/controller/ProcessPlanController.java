package com.ruoyi.project.pipe.processPlan.controller;

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
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/admin/pipe/processPlan")
public class ProcessPlanController extends BaseController
{
    private String prefix = "pipe/processPlan";
	
	@Autowired
	private IProcessPlanService processPlanService;
	
	@RequiresPermissions("pipe:processPlan:view")
	@GetMapping()
	public String processPlan()
	{
	    return prefix + "/processPlan";
	}
	
	/**
	 * 查询下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan列表
	 */
	@RequiresPermissions("pipe:processPlan:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProcessPlan processPlan)
	{
		startPage();
        List<ProcessPlan> list = processPlanService.selectProcessPlanList(processPlan);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan列表
	 */
	@RequiresPermissions("pipe:processPlan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProcessPlan processPlan)
    {
    	List<ProcessPlan> list = processPlanService.selectProcessPlanList(processPlan);
        ExcelUtil<ProcessPlan> util = new ExcelUtil<ProcessPlan>(ProcessPlan.class);
        return util.exportExcel(list, "processPlan");
    }
	
	/**
	 * 新增下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
	 */
	@RequiresPermissions("pipe:processPlan:add")
	@Log(title = "下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProcessPlan processPlan)
	{		
		return toAjax(processPlanService.insertProcessPlan(processPlan));
	}

	/**
	 * 修改下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ProcessPlan processPlan = processPlanService.selectProcessPlanById(id);
		mmap.put("processPlan", processPlan);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
	 */
	@RequiresPermissions("pipe:processPlan:edit")
	@Log(title = "下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProcessPlan processPlan)
	{		
		return toAjax(processPlanService.updateProcessPlan(processPlan));
	}
	
	/**
	 * 删除下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan
	 */
	@RequiresPermissions("pipe:processPlan:remove")
	@Log(title = "下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(processPlanService.deleteProcessPlanByIds(ids));
	}
	
}
