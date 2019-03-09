package com.ruoyi.project.process.pipeProcessing.controller;

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
import com.ruoyi.project.process.pipeProcessing.domain.PipeProcessing;
import com.ruoyi.project.process.pipeProcessing.service.IPipeProcessingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管件加工 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/pipeProcessing")
public class PipeProcessingController extends BaseController
{
    private String prefix = "process/pipeProcessing";
	
	@Autowired
	private IPipeProcessingService pipeProcessingService;
	
	@RequiresPermissions("process:pipeProcessing:view")
	@GetMapping()
	public String pipeProcessing()
	{
	    return prefix + "/pipeProcessing";
	}
	
	/**
	 * 查询管件加工列表
	 */
	@RequiresPermissions("process:pipeProcessing:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipeProcessing pipeProcessing)
	{
		startPage();
        List<PipeProcessing> list = pipeProcessingService.selectPipeProcessingList(pipeProcessing);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出管件加工列表
	 */
	@RequiresPermissions("process:pipeProcessing:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipeProcessing pipeProcessing)
    {
    	List<PipeProcessing> list = pipeProcessingService.selectPipeProcessingList(pipeProcessing);
        ExcelUtil<PipeProcessing> util = new ExcelUtil<PipeProcessing>(PipeProcessing.class);
        return util.exportExcel(list, "pipeProcessing");
    }
	
	/**
	 * 新增管件加工
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存管件加工
	 */
	@RequiresPermissions("process:pipeProcessing:add")
	@Log(title = "管件加工", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipeProcessing pipeProcessing)
	{		
		return toAjax(pipeProcessingService.insertPipeProcessing(pipeProcessing));
	}

	/**
	 * 修改管件加工
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		PipeProcessing pipeProcessing = pipeProcessingService.selectPipeProcessingById(id);
		mmap.put("pipeProcessing", pipeProcessing);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存管件加工
	 */
	@RequiresPermissions("process:pipeProcessing:edit")
	@Log(title = "管件加工", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipeProcessing pipeProcessing)
	{		
		return toAjax(pipeProcessingService.updatePipeProcessing(pipeProcessing));
	}
	
	/**
	 * 删除管件加工
	 */
	@RequiresPermissions("process:pipeProcessing:remove")
	@Log(title = "管件加工", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipeProcessingService.deletePipeProcessingByIds(ids));
	}
	
}
