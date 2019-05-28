package com.ruoyi.project.process.taoliaoOnline.controller;

import java.util.List;

import com.ruoyi.project.algorithm.AlgorithmConstants;
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import com.ruoyi.project.process.taoliaoOnline.domain.InputOriginId;
import com.ruoyi.project.process.taoliaoOnline.domain.InputOriginInfo;
import com.ruoyi.project.process.taoliaoOnline.domain.OriginInfo;
import com.ruoyi.project.process.taoliaoOrigin.service.ITaoliaoOriginService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.process.taoliaoOnline.service.ITaoliaoOnlineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 在线套料 信息操作处理
 *
 * @author ricardo
 * @date 2019-04-19
 */
@Controller
@RequestMapping("/admin/process/taoliaoOnline")
public class TaoliaoOnlineController extends BaseController {
    private String prefix = "process/taoliaoOnline";

    @Autowired
    private ITaoliaoService taoliaoService;
    @Autowired
    private ITaoliaoOnlineService taoliaoOnlineService;
    @Autowired
    private ITaoliaoOriginService taoliaoOriginService;

    @RequiresPermissions("process:taoliaoOnline:view")
    @GetMapping()
    public String taoliaoOnline() {
        return prefix + "/taoliaoOnline";
    }

    /**
     * 查询在线套料列表
     */
    @RequiresPermissions("process:taoliaoOnline:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Taoliao taoliao) {
        startPage();
        List<Taoliao> list = taoliaoService.selectTaoliaoList(taoliao);
        return getDataTable(list);
    }


    /**
     * 导出在线套料列表
     */
    @RequiresPermissions("process:taoliaoOnline:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Taoliao taoliao) {
        List<Taoliao> list = taoliaoService.selectTaoliaoList(taoliao);
        ExcelUtil<Taoliao> util = new ExcelUtil<Taoliao>(Taoliao.class);
        return util.exportExcel(list, "taoliaoOnline");
    }

    /**
     * 新增在线套料
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存在线套料
     */
    @RequiresPermissions("process:taoliaoOnline:add")
    @Log(title = "在线套料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Taoliao taoliaoOnline) {
        return toAjax(taoliaoService.insertTaoliao(taoliaoOnline));
    }

    /**
     * 修改在线套料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Taoliao taoliaoOnline = taoliaoService.selectTaoliaoById(id);
        mmap.put("taoliaoOnline", taoliaoOnline);
        return prefix + "/edit";
    }

    /**
     * 修改保存在线套料
     */
    @RequiresPermissions("process:taoliaoOnline:edit")
    @Log(title = "在线套料", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/edit", consumes = "application/json")
    @ResponseBody
    public AjaxResult editSave(@RequestBody InputOriginId inputOriginId) {
        //if(inputOriginInfo)@RequestBody List<OriginInfo> originInfoList, Taoliao taoliao
        System.out.println(inputOriginId.getOriginInfoList().toString());
        System.out.println(inputOriginId.getId());
        //Taoliao taoliaoOnline = inputOriginInfo.getTaoliao();
        Taoliao taoliao = taoliaoService.selectTaoliaoById(inputOriginId.getId());
        List<OriginInfo> originInfoList = inputOriginId.getOriginInfoList();

        taoliaoOnlineService.inputOrigin(taoliao,originInfoList,AlgorithmConstants.DynamicProgrammingName);
        taoliaoOnlineService.inputOrigin(taoliao,originInfoList,AlgorithmConstants.GeneticAlgorithmName);

        taoliaoOnlineService.dynamicProgramming(taoliao);
        return toAjax(1);
    }

    /**
     * 删除在线套料
     */
    @RequiresPermissions("process:taoliaoOnline:remove")
    @Log(title = "在线套料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(taoliaoService.deleteTaoliaoByIds(ids));
    }

}
