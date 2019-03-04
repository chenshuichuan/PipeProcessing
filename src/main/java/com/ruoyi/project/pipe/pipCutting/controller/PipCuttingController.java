package com.ruoyi.project.pipe.pipCutting.controller;

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
import com.ruoyi.project.pipe.pipCutting.domain.PipCutting;
import com.ruoyi.project.pipe.pipCutting.service.IPipCuttingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 下料 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/pipCutting")
public class PipCuttingController extends BaseController
{
    private String prefix = "pipe/pipCutting";
	
	@Autowired
	private IPipCuttingService pipCuttingService;
	
	@RequiresPermissions("pipe:pipCutting:view")
	@GetMapping()
	public String pipCutting()
	{
	    return prefix + "/pipCutting";
	}
	
	/**
	 * 查询下料列表
	 */
	@RequiresPermissions("pipe:pipCutting:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipCutting pipCutting)
	{
		startPage();
        List<PipCutting> list = pipCuttingService.selectPipCuttingList(pipCutting);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出下料列表
	 */
	@RequiresPermissions("pipe:pipCutting:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipCutting pipCutting)
    {
    	List<PipCutting> list = pipCuttingService.selectPipCuttingList(pipCutting);
        ExcelUtil<PipCutting> util = new ExcelUtil<PipCutting>(PipCutting.class);
        return util.exportExcel(list, "pipCutting");
    }
	
	/**
	 * 新增下料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存下料
	 */
	@RequiresPermissions("pipe:pipCutting:add")
	@Log(title = "下料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipCutting pipCutting)
	{		
		return toAjax(pipCuttingService.insertPipCutting(pipCutting));
	}

	/**
	 * 修改下料
	 */
	@GetMapping("/edit/{oldId}")
	public String edit(@PathVariable("oldId") Integer oldId, ModelMap mmap)
	{
		PipCutting pipCutting = pipCuttingService.selectPipCuttingById(oldId);
		mmap.put("pipCutting", pipCutting);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存下料
	 */
	@RequiresPermissions("pipe:pipCutting:edit")
	@Log(title = "下料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipCutting pipCutting)
	{		
		return toAjax(pipCuttingService.updatePipCutting(pipCutting));
	}
	
	/**
	 * 删除下料
	 */
	@RequiresPermissions("pipe:pipCutting:remove")
	@Log(title = "下料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipCuttingService.deletePipCuttingByIds(ids));
	}
	
}
