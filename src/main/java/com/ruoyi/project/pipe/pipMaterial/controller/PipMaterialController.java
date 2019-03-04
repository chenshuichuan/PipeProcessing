package com.ruoyi.project.pipe.pipMaterial.controller;

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
import com.ruoyi.project.pipe.pipMaterial.domain.PipMaterial;
import com.ruoyi.project.pipe.pipMaterial.service.IPipMaterialService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管子材料 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/pipMaterial")
public class PipMaterialController extends BaseController
{
    private String prefix = "pipe/pipMaterial";
	
	@Autowired
	private IPipMaterialService pipMaterialService;
	
	@RequiresPermissions("pipe:pipMaterial:view")
	@GetMapping()
	public String pipMaterial()
	{
	    return prefix + "/pipMaterial";
	}
	
	/**
	 * 查询管子材料列表
	 */
	@RequiresPermissions("pipe:pipMaterial:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipMaterial pipMaterial)
	{
		startPage();
        List<PipMaterial> list = pipMaterialService.selectPipMaterialList(pipMaterial);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出管子材料列表
	 */
	@RequiresPermissions("pipe:pipMaterial:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipMaterial pipMaterial)
    {
    	List<PipMaterial> list = pipMaterialService.selectPipMaterialList(pipMaterial);
        ExcelUtil<PipMaterial> util = new ExcelUtil<PipMaterial>(PipMaterial.class);
        return util.exportExcel(list, "pipMaterial");
    }
	
	/**
	 * 新增管子材料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存管子材料
	 */
	@RequiresPermissions("pipe:pipMaterial:add")
	@Log(title = "管子材料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipMaterial pipMaterial)
	{		
		return toAjax(pipMaterialService.insertPipMaterial(pipMaterial));
	}

	/**
	 * 修改管子材料
	 */
	@GetMapping("/edit/{oldId}")
	public String edit(@PathVariable("oldId") Integer oldId, ModelMap mmap)
	{
		PipMaterial pipMaterial = pipMaterialService.selectPipMaterialById(oldId);
		mmap.put("pipMaterial", pipMaterial);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存管子材料
	 */
	@RequiresPermissions("pipe:pipMaterial:edit")
	@Log(title = "管子材料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipMaterial pipMaterial)
	{		
		return toAjax(pipMaterialService.updatePipMaterial(pipMaterial));
	}
	
	/**
	 * 删除管子材料
	 */
	@RequiresPermissions("pipe:pipMaterial:remove")
	@Log(title = "管子材料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipMaterialService.deletePipMaterialByIds(ids));
	}
	
}
