package com.ruoyi.project.process.batchProcessing.controller;

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
import com.ruoyi.project.process.batchProcessing.domain.BatchProcessing;
import com.ruoyi.project.process.batchProcessing.service.IBatchProcessingService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 批次加工 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/batchProcessing")
public class BatchProcessingController extends BaseController
{
    private String prefix = "process/batchProcessing";
	
	@Autowired
	private IBatchProcessingService batchProcessingService;
	
	@RequiresPermissions("process:batchProcessing:view")
	@GetMapping()
	public String batchProcessing()
	{
	    return prefix + "/batchProcessing";
	}
	
	/**
	 * 查询批次加工列表
	 */
	@RequiresPermissions("process:batchProcessing:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BatchProcessing batchProcessing)
	{
		startPage();
        List<BatchProcessing> list = batchProcessingService.selectBatchProcessingList(batchProcessing);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出批次加工列表
	 */
	@RequiresPermissions("process:batchProcessing:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BatchProcessing batchProcessing)
    {
    	List<BatchProcessing> list = batchProcessingService.selectBatchProcessingList(batchProcessing);
        ExcelUtil<BatchProcessing> util = new ExcelUtil<BatchProcessing>(BatchProcessing.class);
        return util.exportExcel(list, "batchProcessing");
    }
	
	/**
	 * 新增批次加工
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存批次加工
	 */
	@RequiresPermissions("process:batchProcessing:add")
	@Log(title = "批次加工", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BatchProcessing batchProcessing)
	{		
		return toAjax(batchProcessingService.insertBatchProcessing(batchProcessing));
	}

	/**
	 * 修改批次加工
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		BatchProcessing batchProcessing = batchProcessingService.selectBatchProcessingById(id);
		mmap.put("batchProcessing", batchProcessing);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存批次加工
	 */
	@RequiresPermissions("process:batchProcessing:edit")
	@Log(title = "批次加工", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BatchProcessing batchProcessing)
	{		
		return toAjax(batchProcessingService.updateBatchProcessing(batchProcessing));
	}
	
	/**
	 * 删除批次加工
	 */
	@RequiresPermissions("process:batchProcessing:remove")
	@Log(title = "批次加工", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(batchProcessingService.deleteBatchProcessingByIds(ids));
	}
	
}
