package com.ruoyi.project.process.scanCode.controller;

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
import com.ruoyi.project.process.scanCode.domain.ScanCode;
import com.ruoyi.project.process.scanCode.service.IScanCodeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 扫码记录 信息操作处理
 *
 * @author ricardo
 * @date 2019-11-16
 */
@Controller
@RequestMapping("/admin/process/scanCode")
public class ScanCodeController extends BaseController {
  private String prefix = "process/scanCode";

  @Autowired
  private IScanCodeService scanCodeService;

  @RequiresPermissions("process:scanCode:view")
  @GetMapping()
  public String scanCode() {
    return prefix + "/scanCode";
  }

  @GetMapping("/scan")
  public String scan() {
    return prefix + "/scan";
  }

  /**
   * 新增保存扫码记录
   */
  @Log(title = "扫码记录", businessType = BusinessType.INSERT)
  @PostMapping("/scan")
  @ResponseBody
  public AjaxResult addScan(ScanCode scanCode) {
    return toAjax(scanCodeService.insertScanCode(scanCode));
  }

  /**
   * 查询扫码记录列表
   */
  //@RequiresPermissions("process:scanCode:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(ScanCode scanCode) {
    startPage();
    List<ScanCode> list = scanCodeService.selectScanCodeList(scanCode);
    return getDataTable(list);
  }


  /**
   * 导出扫码记录列表
   */
  @RequiresPermissions("process:scanCode:export")
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(ScanCode scanCode) {
    List<ScanCode> list = scanCodeService.selectScanCodeList(scanCode);
    ExcelUtil<ScanCode> util = new ExcelUtil<ScanCode>(ScanCode.class);
    return util.exportExcel(list, "scanCode");
  }

  /**
   * 新增扫码记录
   */
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }

  /**
   * 新增保存扫码记录
   */
  @RequiresPermissions("process:scanCode:add")
  @Log(title = "扫码记录", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(ScanCode scanCode) {
    return toAjax(scanCodeService.insertScanCode(scanCode));
  }

  /**
   * 修改扫码记录
   */
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
    ScanCode scanCode = scanCodeService.selectScanCodeById(id);
    mmap.put("scanCode", scanCode);
    return prefix + "/edit";
  }

  /**
   * 修改保存扫码记录
   */
  @RequiresPermissions("process:scanCode:edit")
  @Log(title = "扫码记录", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(ScanCode scanCode) {
    return toAjax(scanCodeService.updateScanCode(scanCode));
  }

  /**
   * 删除扫码记录
   */
  @RequiresPermissions("process:scanCode:remove")
  @Log(title = "扫码记录", businessType = BusinessType.DELETE)
  @PostMapping("/remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    return toAjax(scanCodeService.deleteScanCodeByIds(ids));
  }

}
