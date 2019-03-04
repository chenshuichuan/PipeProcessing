package com.ruoyi.project.pipe.pipStore.controller;

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
import com.ruoyi.project.pipe.pipStore.domain.PipStore;
import com.ruoyi.project.pipe.pipStore.service.IPipStoreService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管子存放 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/pipStore")
public class PipStoreController extends BaseController
{
    private String prefix = "pipe/pipStore";
	
	@Autowired
	private IPipStoreService pipStoreService;
	
	@RequiresPermissions("pipe:pipStore:view")
	@GetMapping()
	public String pipStore()
	{
	    return prefix + "/pipStore";
	}
	
	/**
	 * 查询管子存放列表
	 */
	@RequiresPermissions("pipe:pipStore:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(PipStore pipStore)
	{
		startPage();
        List<PipStore> list = pipStoreService.selectPipStoreList(pipStore);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出管子存放列表
	 */
	@RequiresPermissions("pipe:pipStore:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PipStore pipStore)
    {
    	List<PipStore> list = pipStoreService.selectPipStoreList(pipStore);
        ExcelUtil<PipStore> util = new ExcelUtil<PipStore>(PipStore.class);
        return util.exportExcel(list, "pipStore");
    }
	
	/**
	 * 新增管子存放
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存管子存放
	 */
	@RequiresPermissions("pipe:pipStore:add")
	@Log(title = "管子存放", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(PipStore pipStore)
	{		
		return toAjax(pipStoreService.insertPipStore(pipStore));
	}

	/**
	 * 修改管子存放
	 */
	@GetMapping("/edit/{oldId}")
	public String edit(@PathVariable("oldId") Integer oldId, ModelMap mmap)
	{
		PipStore pipStore = pipStoreService.selectPipStoreById(oldId);
		mmap.put("pipStore", pipStore);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存管子存放
	 */
	@RequiresPermissions("pipe:pipStore:edit")
	@Log(title = "管子存放", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(PipStore pipStore)
	{		
		return toAjax(pipStoreService.updatePipStore(pipStore));
	}
	
	/**
	 * 删除管子存放
	 */
	@RequiresPermissions("pipe:pipStore:remove")
	@Log(title = "管子存放", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(pipStoreService.deletePipStoreByIds(ids));
	}
	
}
