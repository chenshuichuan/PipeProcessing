package com.ruoyi.project.process.taoliaoResult.controller;

import java.util.List;

import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import com.ruoyi.project.process.taoliaoResult.service.ITaoliaoResultService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 管材套料结果 信息操作处理
 *
 * @author ricardo
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/admin/process/taoliaoResult")
public class TaoliaoResultController extends BaseController {
  private String prefix = "process/taoliaoResult";

  @Autowired
  private ITaoliaoResultService taoliaoResultService;
  @Autowired
  private ITaoliaoService taoliaoService;

  @RequiresPermissions("process:taoliaoResult:view")
  @GetMapping()
  public String taoliaoResult() {
    return prefix + "/taoliaoResult";
  }

  /**
   * 查询管材套料结果列表
   */
  @RequiresPermissions("process:taoliaoResult:list")
  @PostMapping("/list")
  @ResponseBody
  public TableDataInfo list(TaoliaoResult taoliaoResult) {
    startPage();
    List<TaoliaoResult> list = taoliaoResultService.selectTaoliaoResultList(taoliaoResult);
    return getDataTable(list);
  }


  /**
   * 导出管材套料结果列表
   */
  @RequiresPermissions("process:taoliaoResult:export")
  @PostMapping("/export")
  @ResponseBody
  public AjaxResult export(TaoliaoResult taoliaoResult) {
    List<TaoliaoResult> list = taoliaoResultService.selectTaoliaoResultList(taoliaoResult);
    ExcelUtil<TaoliaoResult> util = new ExcelUtil<TaoliaoResult>(TaoliaoResult.class);
    return util.exportExcel(list, "taoliaoResult");
  }

  /**
   * 新增管材套料结果
   */
  @GetMapping("/add")
  public String add() {
    return prefix + "/add";
  }

  /**
   * 新增保存管材套料结果
   */
  @RequiresPermissions("process:taoliaoResult:add")
  @Log(title = "管材套料结果", businessType = BusinessType.INSERT)
  @PostMapping("/add")
  @ResponseBody
  public AjaxResult addSave(TaoliaoResult taoliaoResult) {
    return toAjax(taoliaoResultService.insertTaoliaoResult(taoliaoResult));
  }

  /**
   * 修改管材套料结果
   */
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
    TaoliaoResult taoliaoResult = taoliaoResultService.selectTaoliaoResultById(id);
    mmap.put("taoliaoResult", taoliaoResult);
    return prefix + "/edit";
  }

  /**
   * 修改保存管材套料结果
   */
  @RequiresPermissions("process:taoliaoResult:edit")
  @Log(title = "管材套料结果", businessType = BusinessType.UPDATE)
  @PostMapping("/edit")
  @ResponseBody
  public AjaxResult editSave(TaoliaoResult taoliaoResult) {
    return toAjax(taoliaoResultService.updateTaoliaoResult(taoliaoResult));
  }

  /**
   * 删除管材套料结果
   */
  @RequiresPermissions("process:taoliaoResult:remove")
  @Log(title = "管材套料结果", businessType = BusinessType.DELETE)
  @PostMapping("/remove")
  @ResponseBody
  public AjaxResult remove(String ids) {
    return toAjax(taoliaoResultService.deleteTaoliaoResultByIds(ids));
  }

  /**
   * 修改管材套料结果
   */
  @GetMapping("/print")
  public String printAll(@RequestParam("resultTaoliaoId") Integer resultTaoliaoId,
                         @RequestParam("resultAlgorithm") String resultAlgorithm, ModelMap mmap) {
    Taoliao taoliao = taoliaoService.selectTaoliaoById(resultTaoliaoId);

    TaoliaoResult taoliaoResult = new TaoliaoResult();
    taoliaoResult.setTaoliaoId(resultTaoliaoId);
    taoliaoResult.setAlgorithm(resultAlgorithm);
    List<TaoliaoResult> list = taoliaoResultService.selectTaoliaoResultList(taoliaoResult);
    mmap.put("taoliaoResultList", list);
    mmap.put("resultSize", list.size());
    if(taoliao!=null) {
      mmap.put("pipeMaterial", taoliao.getPipeMaterial());
    }else{
      mmap.put("pipeMaterial", "查找错误！");
    }
    return prefix + "/print";
  }
  /**
   * 修改管材套料结果
   */
  @RequiresPermissions("process:taoliaoResult:edit")
  @Log(title = "管材套料完成", businessType = BusinessType.UPDATE)
  @PostMapping("/printFinished")
  @ResponseBody
  public AjaxResult printFinished(List<TaoliaoResult> taoliaoResultList) {
    //将该批次管件标记下料完成

    return toAjax(1);
  }
  /**
   * 修改管材套料结果
   */
  @GetMapping("/print25")
  public String print25(@RequestParam("resultTaoliaoId") Integer resultTaoliaoId,
                         @RequestParam("resultAlgorithm") String resultAlgorithm, ModelMap mmap) {
    Taoliao taoliao = taoliaoService.selectTaoliaoById(resultTaoliaoId);

    TaoliaoResult taoliaoResult = new TaoliaoResult();
    taoliaoResult.setTaoliaoId(resultTaoliaoId);
    taoliaoResult.setAlgorithm(resultAlgorithm);
    List<TaoliaoResult> list = taoliaoResultService.selectTaoliaoResultList(taoliaoResult);
    mmap.put("taoliaoResultList", list);
    mmap.put("resultSize", list.size());
    if(taoliao!=null) {
      mmap.put("pipeMaterial", taoliao.getPipeMaterial());
    }else{
      mmap.put("pipeMaterial", "查找错误！");
    }
    return prefix + "/print25";
  }
}
