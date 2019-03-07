package com.ruoyi.project.pipe.pipPipe.controller;

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
import com.ruoyi.project.pipe.pipPipe.domain.PipPipe;
import com.ruoyi.project.pipe.pipPipe.service.IPipPipeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 中间数据库Pipe 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-06
 */
@Controller
@RequestMapping("/admin/pipe/pipPipe")
public class PipPipeController extends BaseController
{
    private String prefix = "pipe/pipPipe";
	
	@Autowired
	private IPipPipeService pipPipeService;
	
	@RequiresPermissions("pipe:pipPipe:view")
	@GetMapping()
	public String pipPipe()
	{
	    return prefix + "/pipPipe";
	}
	
	/**
	 * 查询中间数据库Pipe列表
	 */
	@RequiresPermissions("pipe:pipPipe:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipPipe pipPipe)
	{
		startPage();
        List<PipPipe> list = pipPipeService.selectPipPipeList(pipPipe);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出中间数据库Pipe列表
	 */
	@RequiresPermissions("pipe:pipPipe:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipPipe pipPipe)
    {
    	List<PipPipe> list = pipPipeService.selectPipPipeList(pipPipe);
        ExcelUtil<PipPipe> util = new ExcelUtil<PipPipe>(PipPipe.class);
        return util.exportExcel(list, "pipPipe");
    }
	
	/**
	 * 新增中间数据库Pipe
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存中间数据库Pipe
	 */
	@RequiresPermissions("pipe:pipPipe:add")
	@Log(title = "中间数据库Pipe", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipPipe pipPipe)
	{		
		return toAjax(pipPipeService.insertPipPipe(pipPipe));
	}

	/**
	 * 修改中间数据库Pipe
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PipPipe pipPipe = pipPipeService.selectPipPipeById(id);
		mmap.put("pipPipe", pipPipe);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存中间数据库Pipe
	 */
	@RequiresPermissions("pipe:pipPipe:edit")
	@Log(title = "中间数据库Pipe", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipPipe pipPipe)
	{		
		return toAjax(pipPipeService.updatePipPipe(pipPipe));
	}
	
	/**
	 * 删除中间数据库Pipe
	 */
	@RequiresPermissions("pipe:pipPipe:remove")
	@Log(title = "中间数据库Pipe", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipPipeService.deletePipPipeByIds(ids));
	}
	
}
