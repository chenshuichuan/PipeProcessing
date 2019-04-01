package com.ruoyi.project.process.middleStatus.controller;

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
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.IMiddleStatusService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 中间数据读取程序状态 信息操作处理
 * 
 * @author ricardo
 * @date 2019-04-01
 */
@Controller
@RequestMapping("/admin/process/middleStatus")
public class MiddleStatusController extends BaseController
{
    private String prefix = "process/middleStatus";
	
	@Autowired
	private IMiddleStatusService middleStatusService;
	
	@RequiresPermissions("process:middleStatus:view")
	@GetMapping()
	public String middleStatus()
	{
	    return prefix + "/middleStatus";
	}
	
	/**
	 * 查询中间数据读取程序状态列表
	 */
	@RequiresPermissions("process:middleStatus:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(MiddleStatus middleStatus)
	{
		startPage();
        List<MiddleStatus> list = middleStatusService.selectMiddleStatusList(middleStatus);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出中间数据读取程序状态列表
	 */
	@RequiresPermissions("process:middleStatus:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MiddleStatus middleStatus)
    {
    	List<MiddleStatus> list = middleStatusService.selectMiddleStatusList(middleStatus);
        ExcelUtil<MiddleStatus> util = new ExcelUtil<MiddleStatus>(MiddleStatus.class);
        return util.exportExcel(list, "middleStatus");
    }
	
	/**
	 * 新增中间数据读取程序状态
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存中间数据读取程序状态
	 */
	@RequiresPermissions("process:middleStatus:add")
	@Log(title = "中间数据读取程序状态", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(MiddleStatus middleStatus)
	{		
		return toAjax(middleStatusService.insertMiddleStatus(middleStatus));
	}

	/**
	 * 修改中间数据读取程序状态
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		MiddleStatus middleStatus = middleStatusService.selectMiddleStatusById(id);
		mmap.put("middleStatus", middleStatus);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存中间数据读取程序状态
	 */
	@RequiresPermissions("process:middleStatus:edit")
	@Log(title = "中间数据读取程序状态", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(MiddleStatus middleStatus)
	{		
		return toAjax(middleStatusService.updateMiddleStatus(middleStatus));
	}
	
	/**
	 * 删除中间数据读取程序状态
	 */
	@RequiresPermissions("process:middleStatus:remove")
	@Log(title = "中间数据读取程序状态", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(middleStatusService.deleteMiddleStatusByIds(ids));
	}
	
}
