package com.ruoyi.project.pipe.pipManage.controller;

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
import com.ruoyi.project.pipe.pipManage.domain.PipManage;
import com.ruoyi.project.pipe.pipManage.service.IPipManageService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 派工单 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/pipManage")
public class PipManageController extends BaseController
{
    private String prefix = "pipe/pipManage";
	
	@Autowired
	private IPipManageService pipManageService;
	
	@RequiresPermissions("pipe:pipManage:view")
	@GetMapping()
	public String pipManage()
	{
	    return prefix + "/pipManage";
	}
	
	/**
	 * 查询派工单列表
	 */
	@RequiresPermissions("pipe:pipManage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipManage pipManage)
	{
		startPage();
        List<PipManage> list = pipManageService.selectPipManageList(pipManage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出派工单列表
	 */
	@RequiresPermissions("pipe:pipManage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipManage pipManage)
    {
    	List<PipManage> list = pipManageService.selectPipManageList(pipManage);
        ExcelUtil<PipManage> util = new ExcelUtil<PipManage>(PipManage.class);
        return util.exportExcel(list, "pipManage");
    }
	
	/**
	 * 新增派工单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存派工单
	 */
	@RequiresPermissions("pipe:pipManage:add")
	@Log(title = "派工单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipManage pipManage)
	{		
		return toAjax(pipManageService.insertPipManage(pipManage));
	}

	/**
	 * 修改派工单
	 */
	@GetMapping("/edit/{oldId}")
	public String edit(@PathVariable("oldId") Integer oldId, ModelMap mmap)
	{
		PipManage pipManage = pipManageService.selectPipManageById(oldId);
		mmap.put("pipManage", pipManage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存派工单
	 */
	@RequiresPermissions("pipe:pipManage:edit")
	@Log(title = "派工单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipManage pipManage)
	{		
		return toAjax(pipManageService.updatePipManage(pipManage));
	}
	
	/**
	 * 删除派工单
	 */
	@RequiresPermissions("pipe:pipManage:remove")
	@Log(title = "派工单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipManageService.deletePipManageByIds(ids));
	}
	
}
