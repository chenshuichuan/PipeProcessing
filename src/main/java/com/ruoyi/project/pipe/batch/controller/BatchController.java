package com.ruoyi.project.pipe.batch.controller;

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
import com.ruoyi.project.pipe.batch.domain.Batch;
import com.ruoyi.project.pipe.batch.service.IBatchService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 加工批次 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/batch")
public class BatchController extends BaseController
{
    private String prefix = "pipe/batch";
	
	@Autowired
	private IBatchService batchService;
	
	@RequiresPermissions("pipe:batch:view")
	@GetMapping()
	public String batch()
	{
	    return prefix + "/batch";
	}
	
	/**
	 * 查询加工批次列表
	 */
	@RequiresPermissions("pipe:batch:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Batch batch)
	{
		startPage();
        List<Batch> list = batchService.selectBatchList(batch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出加工批次列表
	 */
	@RequiresPermissions("pipe:batch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Batch batch)
    {
    	List<Batch> list = batchService.selectBatchList(batch);
        ExcelUtil<Batch> util = new ExcelUtil<Batch>(Batch.class);
        return util.exportExcel(list, "batch");
    }
	
	/**
	 * 新增加工批次
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存加工批次
	 */
	@RequiresPermissions("pipe:batch:add")
	@Log(title = "加工批次", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Batch batch)
	{		
		return toAjax(batchService.insertBatch(batch));
	}

	/**
	 * 修改加工批次
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Batch batch = batchService.selectBatchById(id);
		mmap.put("batch", batch);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存加工批次
	 */
	@RequiresPermissions("pipe:batch:edit")
	@Log(title = "加工批次", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Batch batch)
	{		
		return toAjax(batchService.updateBatch(batch));
	}
	
	/**
	 * 删除加工批次
	 */
	@RequiresPermissions("pipe:batch:remove")
	@Log(title = "加工批次", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(batchService.deleteBatchByIds(ids));
	}
	
}
