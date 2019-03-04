package com.ruoyi.project.pipe.pipe.controller;

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
import com.ruoyi.project.pipe.pipe.domain.Pipe;
import com.ruoyi.project.pipe.pipe.service.IPipeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管件 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/pipe")
public class PipeController extends BaseController
{
    private String prefix = "pipe/pipe";
	
	@Autowired
	private IPipeService pipeService;
	
	@RequiresPermissions("pipe:pipe:view")
	@GetMapping()
	public String pipe()
	{
	    return prefix + "/pipe";
	}
	
	/**
	 * 查询管件列表
	 */
	@RequiresPermissions("pipe:pipe:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Pipe pipe)
	{
		startPage();
        List<Pipe> list = pipeService.selectPipeList(pipe);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出管件列表
	 */
	@RequiresPermissions("pipe:pipe:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Pipe pipe)
    {
    	List<Pipe> list = pipeService.selectPipeList(pipe);
        ExcelUtil<Pipe> util = new ExcelUtil<Pipe>(Pipe.class);
        return util.exportExcel(list, "pipe");
    }
	
	/**
	 * 新增管件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存管件
	 */
	@RequiresPermissions("pipe:pipe:add")
	@Log(title = "管件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Pipe pipe)
	{		
		return toAjax(pipeService.insertPipe(pipe));
	}

	/**
	 * 修改管件
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Pipe pipe = pipeService.selectPipeById(id);
		mmap.put("pipe", pipe);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存管件
	 */
	@RequiresPermissions("pipe:pipe:edit")
	@Log(title = "管件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Pipe pipe)
	{		
		return toAjax(pipeService.updatePipe(pipe));
	}
	
	/**
	 * 删除管件
	 */
	@RequiresPermissions("pipe:pipe:remove")
	@Log(title = "管件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipeService.deletePipeByIds(ids));
	}
	
}
