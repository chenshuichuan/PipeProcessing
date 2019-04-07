package com.ruoyi.project.process.batchArrange.controller;

import java.util.List;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.cutPlan.service.ICutPlanService;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.mapper.WorkplaceMapper;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.project.process.batchArrange.service.IBatchArrangeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 批次派工 信息操作处理
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Controller
@RequestMapping("/admin/process/batchArrange")
public class BatchArrangeController extends BaseController {
    private static final Logger logger=LoggerFactory.getLogger(BatchArrangeController.class);

    private String prefix = "process/batchArrange";

    @Autowired
    private IBatchArrangeService batchArrangeService;
    @Autowired
    private ICutPlanService cutPlanService;
    @Autowired
    private IWorkplaceService workplaceService;
    @Autowired
    private WorkplaceMapper workplaceMapper;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;

    @RequiresPermissions("process:batchArrange:view")
    @GetMapping()
    public String batchArrange() {
        return prefix + "/batchArrange";
    }

    /**
     * 查询批次派工列表
     */
    @RequiresPermissions("process:batchArrange:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CutPlan cutPlan) {
        startPage();
        List<CutPlan> list = cutPlanService.selectCutPlanList(cutPlan);
        return getDataTable(list);
    }


    /**
     * 导出批次派工列表
     */
    @RequiresPermissions("process:batchArrange:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CutPlan batchArrange) {
        List<CutPlan> list = cutPlanService.selectCutPlanList(batchArrange);
        ExcelUtil<CutPlan> util = new ExcelUtil<CutPlan>(CutPlan.class);
        return util.exportExcel(list, "batchArrange");
    }

    /**
     * 新增批次派工
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存批次派工
     */
    @RequiresPermissions("process:batchArrange:add")
    @Log(title = "批次派工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CutPlan cutPlan) {
        return toAjax(cutPlanService.insertCutPlan(cutPlan));
    }

    /**
     * 修改批次派工
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        CutPlan cutPlan = cutPlanService.selectCutPlanById(id);
        String[] strings = {"一部大管下料工段","一部下料工段","二部下料工段"};

        if(cutPlan.getOnebigCutNumber()>0){
            mmap.put("OnebigCut", getCutplace(cutPlan,strings[0]));
        }
        if(cutPlan.getOneTotalNumber()>0){
            mmap.put("OneCut", getCutplace(cutPlan,strings[1]));
        }
        if(cutPlan.getTwoTotalNumber()>0){
            mmap.put("TwoCut", getCutplace(cutPlan,strings[2]));
        }

        mmap.put("batchArrange", cutPlan);
        return prefix + "/edit";
    }

    private Workplace getCutplace(CutPlan cutPlan, String name) {
        Workplace workplace = new Workplace();
        workplace.setWorkplaceName(name);
        List<Workplace> workplaceList = workplaceMapper.selectWorkplaceList(workplace);
        if(workplaceList==null || workplaceList.size() ==0 ){
            logger.error("下料工段不存在！请检查数据");
            MiddleStatus middleStatus = new MiddleStatus("下料工段不存在！请检查数据",
                    "sys_workplace",cutPlan.toString(),"查询");
            middleStatusRepository.save(middleStatus);
            return  null;
        }
        else {
            if(workplaceList.size()>1) {
                logger.error("下料工段不唯一！请检查数据");
                MiddleStatus middleStatus = new MiddleStatus("下料工段不唯一！请检查数据",
                        "sys_workplace",cutPlan.toString(),"查询");
                middleStatusRepository.save(middleStatus);
            }
            workplace = workplaceList.get(0);
        }
        return workplace;
    }

    /**
     * 修改保存批次派工
     */
    @RequiresPermissions("process:batchArrange:edit")
    @Log(title = "批次派工", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CutPlan cutPlan) {
        return toAjax(cutPlanService.updateCutPlan(cutPlan));
    }

    /**
     * 删除批次派工
     */
    @RequiresPermissions("process:batchArrange:remove")
    @Log(title = "批次派工", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cutPlanService.deleteCutPlanByIds(ids));
    }

}
