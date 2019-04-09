package com.ruoyi.project.pipe.processPlan.controller;

import java.io.File;
import java.util.List;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.project.common.ProjectThreadService;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.project.system.files.domain.Files;
import com.ruoyi.project.system.files.service.FilesRepository;
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
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 下料计划，不包含后续加工，后续加工计划请查看pipe_process_plan 信息操作处理
 *
 * @author ricardo
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/admin/pipe/processPlan")
public class ProcessPlanController extends BaseController {
    private String prefix = "pipe/processPlan";

    @Autowired
    private IProcessPlanService processPlanService;
    @Autowired
    private ProjectThreadService projectThreadService;
    @Autowired
    private FilesRepository filesRepository;
    @Autowired
    private IShipService shipService;

    @RequiresPermissions("pipe:processPlan:view")
    @GetMapping()
    public String processPlan(ModelMap mmap) {
        List<Files> list = filesRepository.findBySuffix("xls");
        mmap.put("planFiles", list);

        return prefix + "/processPlan";
    }

    /**
     * 查询加工计划，，，请查看pipe_process_plan列表
     */
    @RequiresPermissions("pipe:processPlan:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ProcessPlan processPlan) {
        startPage();
        List<ProcessPlan> list = processPlanService.selectProcessPlanList(processPlan);
        System.out.println("size:" + list.size());
        return getDataTable(list);
    }


    /**
     * 导出加工计划
     */
    @RequiresPermissions("pipe:processPlan:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProcessPlan processPlan) {
        List<ProcessPlan> list = processPlanService.selectProcessPlanList(processPlan);
        ExcelUtil<ProcessPlan> util = new ExcelUtil<ProcessPlan>(ProcessPlan.class);
        return util.exportExcel(list, "processPlan");
    }

    /**
     * 新增加工计划请
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存加工计划
     */
    @RequiresPermissions("pipe:processPlan:add")
    @Log(title = "加工计划添加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ProcessPlan processPlan) {
        return toAjax(processPlanService.insertProcessPlan(processPlan));
    }

    /**
     * 修改加工计划
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ProcessPlan processPlan = processPlanService.selectProcessPlanById(id);
        mmap.put("processPlan", processPlan);
        return prefix + "/edit";
    }

    /**
     * 修改保存加工计划
     */
    @RequiresPermissions("pipe:processPlan:edit")
    @Log(title = "加工计划修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ProcessPlan processPlan) {
        return toAjax(processPlanService.updateProcessPlan(processPlan));
    }

    /**
     * 删除加工计划
     */
    @RequiresPermissions("pipe:processPlan:remove")
    @Log(title = "加工计划删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(processPlanService.deleteProcessPlanByIds(ids));
    }

    /**
     * 加工计划解析By计划名称
     */
    @RequiresPermissions("pipe:processPlan:edit")
    @Log(title = "加工计划解析By计划名称", businessType = BusinessType.UPDATE)
    @PostMapping("/analysisPlanByPlanName")
    @ResponseBody
    public AjaxResult analysisPlansByPlanName(String name) {
        int result = processPlanService.judgeBatchUnitByPlanName(name);
        //springboot 开线程，异步解析计划下的单元的加工顺序
        projectThreadService.judgeBatchUnitByPlanName(name);
        return toAjax(result);
    }

    /**
     * 加工计划添加By计划id
     */
    @RequiresPermissions("pipe:processPlan:edit")
    @Log(title = "加工计划解析By计划id", businessType = BusinessType.UPDATE)
    @PostMapping("/analysisProcessPlan")
    @ResponseBody
    public AjaxResult analysisPlan(Integer id)
    {
        ProcessPlan processPlan = processPlanService.selectProcessPlanById(id);
        processPlanService.judgeBatchUnitOfPlan(processPlan);
        return toAjax(processPlanService.analysisOrderOf(processPlan));
    }

    /**
     * 查找简单加工单元
     */
    @PostMapping("/selectPlanNameList")
    @ResponseBody
    public List<String>  selectPlanNameList(Integer isFinished) {
        return processPlanService.selectPlanNameList(isFinished);
    }

}
