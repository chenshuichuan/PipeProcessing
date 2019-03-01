package com.ruoyi.project.system.scanner.controller;

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
import com.ruoyi.project.system.scanner.domain.Scanner;
import com.ruoyi.project.system.scanner.service.IScannerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否 信息操作处理
 * 
 * @author yc
 * @date 2019-02-28
 */
@Controller
@RequestMapping("/admin/system/scanner")
public class ScannerController extends BaseController
{
    private String prefix = "system/scanner";
	
	@Autowired
	private IScannerService scannerService;
	
	@RequiresPermissions("system:scanner:view")
	@GetMapping()
	public String scanner()
	{
	    return prefix + "/scanner";
	}
	
	/**
	 * 查询扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否列表
	 */
	@RequiresPermissions("system:scanner:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Scanner scanner)
	{
		startPage();
        List<Scanner> list = scannerService.selectScannerList(scanner);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否列表
	 */
	@RequiresPermissions("system:scanner:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Scanner scanner)
    {
    	List<Scanner> list = scannerService.selectScannerList(scanner);
        ExcelUtil<Scanner> util = new ExcelUtil<Scanner>(Scanner.class);
        return util.exportExcel(list, "scanner");
    }
	
	/**
	 * 新增扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
	 */
	@RequiresPermissions("system:scanner:add")
	@Log(title = "扫码枪对应", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Scanner scanner)
	{
		return toAjax(scannerService.insertScanner(scanner));
	}

	/**
	 * 修改扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Scanner scanner = scannerService.selectScannerById(id);
		mmap.put("scanner", scanner);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
	 */
	@RequiresPermissions("system:scanner:edit")
	@Log(title = "扫码枪对应", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Scanner scanner)
	{		
		return toAjax(scannerService.updateScanner(scanner));
	}
	
	/**
	 * 删除扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否
	 */
	@RequiresPermissions("system:scanner:remove")
	@Log(title = "扫码枪对应", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(scannerService.deleteScannerByIds(ids));
	}
	
}
