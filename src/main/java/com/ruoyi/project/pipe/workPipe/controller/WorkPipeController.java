package com.ruoyi.project.pipe.workPipe.controller;

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
import com.ruoyi.project.pipe.workPipe.domain.WorkPipe;
import com.ruoyi.project.pipe.workPipe.service.IWorkPipeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管件- 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-07
 */
@Controller
@RequestMapping("/admin/pipe/workPipe")
public class WorkPipeController extends BaseController
{
    private String prefix = "pipe/workPipe";
	
	@Autowired
	private IWorkPipeService workPipeService;
	
	@RequiresPermissions("pipe:workPipe:view")
	@GetMapping()
	public String workPipe()
	{
	    return prefix + "/workPipe";
	}
	
	/**
	 * 查询管件-列表
	 */
	@RequiresPermissions("pipe:workPipe:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WorkPipe workPipe)
	{
		startPage();
        List<WorkPipe> list = workPipeService.selectWorkPipeList(workPipe);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出管件-列表
	 */
	@RequiresPermissions("pipe:workPipe:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WorkPipe workPipe)
    {
    	List<WorkPipe> list = workPipeService.selectWorkPipeList(workPipe);
        ExcelUtil<WorkPipe> util = new ExcelUtil<WorkPipe>(WorkPipe.class);
        return util.exportExcel(list, "workPipe");
    }
	
	/**
	 * 新增管件-
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存管件-
	 */
	@RequiresPermissions("pipe:workPipe:add")
	@Log(title = "管件-", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WorkPipe workPipe)
	{		
		return toAjax(workPipeService.insertWorkPipe(workPipe));
	}

	/**
	 * 修改管件-
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WorkPipe workPipe = workPipeService.selectWorkPipeById(id);
		mmap.put("workPipe", workPipe);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存管件-
	 */
	@RequiresPermissions("pipe:workPipe:edit")
	@Log(title = "管件-", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WorkPipe workPipe)
	{		
		return toAjax(workPipeService.updateWorkPipe(workPipe));
	}
	
	/**
	 * 删除管件-
	 */
	@RequiresPermissions("pipe:workPipe:remove")
	@Log(title = "管件-", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(workPipeService.deleteWorkPipeByIds(ids));
	}
	
}
