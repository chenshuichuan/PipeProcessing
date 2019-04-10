package com.ruoyi.project.process.unitArrange.controller;

import java.util.List;

import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;
import com.ruoyi.project.pipe.processPlan.service.IProcessPlanService;
import com.ruoyi.project.pipe.unit.domain.Unit;
import com.ruoyi.project.pipe.unit.service.IUnitService;
import com.ruoyi.project.process.order.domain.Order;
import com.ruoyi.project.process.order.service.IOrderService;
import com.ruoyi.project.process.unitArrange.domain.UnitArrangeInfo;
import com.ruoyi.project.process.unitArrange.domain.UnitVisual;
import com.ruoyi.project.process.unitArrange.service.IUnitArrangeService;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
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
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 单元派工 信息操作处理
 *
 * @author ricardo
 * @date 2019-03-09
 */
@Controller
@RequestMapping("/admin/process/unitArrange")
public class UnitArrangeController extends BaseController {
    private String prefix = "process/unitArrange";

    @Autowired
    private IUnitService unitService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IWorkplaceService workplaceService;
    @Autowired
    private IUnitArrangeService unitArrangeService;

    @RequiresPermissions("process:unitArrange:view")
    @GetMapping()
    public String unitArrange() {
        return prefix + "/unitArrange";
    }

    /**
     * 查询单元派工列表
     */
    @RequiresPermissions("process:unitArrange:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Unit unit) {
        startPage();
        List<Unit> list = unitService.selectUnitList(unit);
        return getDataTable(list);
    }


    /**
     * 导出单元派工列表
     */
    @RequiresPermissions("process:unitArrange:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Unit unit) {
        List<Unit> list = unitService.selectUnitList(unit);
        ExcelUtil<Unit> util = new ExcelUtil<Unit>(Unit.class);
        return util.exportExcel(list, "unitArrange");
    }

    /**
     * 新增单元派工
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存单元派工
     */
    @RequiresPermissions("process:unitArrange:add")
    @Log(title = "单元派工", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Unit unit) {
        return toAjax(unitService.insertUnit(unit));
    }

    /**
     * 修改单元派工
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Unit unit = unitService.selectUnitById(id);

        //将Unit 转换为UnitArrangeInfo
        Order order = orderService.selectOrderById(unit.getProcessOrderId());
        DictData processStage = orderService.getPipeProcessType(unit.getProcessStageId());
        DictData nextStage = orderService.getPipeProcessType(unit.getNextStageId());
        Workplace section = workplaceService.selectByName(unit.getProcessSection());

        UnitArrangeInfo unitArrangeInfo = new UnitArrangeInfo(unit,order.getOrderListName(),
                processStage.getDictLabel(),nextStage.getDictLabel(),section.getDeptId(),section.getDeptId());
        mmap.put("unitArrange", unitArrangeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存单元派工
     */
    @RequiresPermissions("process:unitArrange:edit")
    @Log(title = "单元派工", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UnitArrangeInfo unitArrangeInfo) {
        System.out.println(unitArrangeInfo.toString());
        return toAjax(unitArrangeService.arrangeByUniteArrangInfo(unitArrangeInfo));
    }

    /**
     * 删除单元派工
     */
    @RequiresPermissions("process:unitArrange:remove")
    @Log(title = "单元派工", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(unitService.deleteUnitByIds(ids));
    }

}
