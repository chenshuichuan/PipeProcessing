package com.ruoyi.project.process.unitArrange.controller;

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
import com.ruoyi.project.process.unitArrange.domain.UnitArrange;
import com.ruoyi.project.process.unitArrange.service.IUnitArrangeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 单元派工 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-09
 */
@Controller
@RequestMapping("/admin/process/unitArrange")
public class UnitArrangeController extends BaseController
{
    private String prefix = "process/unitArrange";
	
	@Autowired
	private IUnitArrangeService unitArrangeService;
	
	@RequiresPermissions("process:unitArrange:view")
	@GetMapping()
	public String unitArrange()
	{
	    return prefix + "/unitArrange";
	}
	
	/**
	 * 查询单元派工列表
	 */
	@RequiresPermissions("process:unitArrange:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(UnitArrange unitArrange)
	{
		startPage();
        List<UnitArrange> list = unitArrangeService.selectUnitArrangeList(unitArrange);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出单元派工列表
	 */
	@RequiresPermissions("process:unitArrange:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UnitArrange unitArrange)
    {
    	List<UnitArrange> list = unitArrangeService.selectUnitArrangeList(unitArrange);
        ExcelUtil<UnitArrange> util = new ExcelUtil<UnitArrange>(UnitArrange.class);
        return util.exportExcel(list, "unitArrange");
    }
	
	/**
	 * 新增单元派工
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存单元派工
	 */
	@RequiresPermissions("process:unitArrange:add")
	@Log(title = "单元派工", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(UnitArrange unitArrange)
	{		
		return toAjax(unitArrangeService.insertUnitArrange(unitArrange));
	}

	/**
	 * 修改单元派工
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		UnitArrange unitArrange = unitArrangeService.selectUnitArrangeById(id);
		mmap.put("unitArrange", unitArrange);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存单元派工
	 */
	@RequiresPermissions("process:unitArrange:edit")
	@Log(title = "单元派工", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(UnitArrange unitArrange)
	{		
		return toAjax(unitArrangeService.updateUnitArrange(unitArrange));
	}
	
	/**
	 * 删除单元派工
	 */
	@RequiresPermissions("process:unitArrange:remove")
	@Log(title = "单元派工", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(unitArrangeService.deleteUnitArrangeByIds(ids));
	}
	
}
