package com.ruoyi.project.process.unitProcessing.controller;

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
import com.ruoyi.project.process.unitProcessing.domain.UnitProcessing;
import com.ruoyi.project.process.unitProcessing.service.IUnitProcessingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 单元加工记录 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/unitProcessing")
public class UnitProcessingController extends BaseController
{
    private String prefix = "process/unitProcessing";
	
	@Autowired
	private IUnitProcessingService unitProcessingService;
	
	@RequiresPermissions("process:unitProcessing:view")
	@GetMapping()
	public String unitProcessing()
	{
	    return prefix + "/unitProcessing";
	}
	
	/**
	 * 查询单元加工记录列表
	 */
	@RequiresPermissions("process:unitProcessing:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UnitProcessing unitProcessing)
	{
		startPage();
        List<UnitProcessing> list = unitProcessingService.selectUnitProcessingList(unitProcessing);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出单元加工记录列表
	 */
	@RequiresPermissions("process:unitProcessing:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UnitProcessing unitProcessing)
    {
    	List<UnitProcessing> list = unitProcessingService.selectUnitProcessingList(unitProcessing);
        ExcelUtil<UnitProcessing> util = new ExcelUtil<UnitProcessing>(UnitProcessing.class);
        return util.exportExcel(list, "unitProcessing");
    }
	
	/**
	 * 新增单元加工记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存单元加工记录
	 */
	@RequiresPermissions("process:unitProcessing:add")
	@Log(title = "单元加工记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UnitProcessing unitProcessing)
	{		
		return toAjax(unitProcessingService.insertUnitProcessing(unitProcessing));
	}

	/**
	 * 修改单元加工记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		UnitProcessing unitProcessing = unitProcessingService.selectUnitProcessingById(id);
		mmap.put("unitProcessing", unitProcessing);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存单元加工记录
	 */
	@RequiresPermissions("process:unitProcessing:edit")
	@Log(title = "单元加工记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UnitProcessing unitProcessing)
	{		
		return toAjax(unitProcessingService.updateUnitProcessing(unitProcessing));
	}
	
	/**
	 * 删除单元加工记录
	 */
	@RequiresPermissions("process:unitProcessing:remove")
	@Log(title = "单元加工记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(unitProcessingService.deleteUnitProcessingByIds(ids));
	}
	
}
