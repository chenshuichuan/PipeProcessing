package com.ruoyi.project.pipe.ship.controller;

import java.util.List;

import com.ruoyi.project.pipe.ship.domain.ShipSimple;
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
import com.ruoyi.project.pipe.ship.domain.Ship;
import com.ruoyi.project.pipe.ship.service.IShipService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 号船 信息操作处理
 *
 * @author ricardo
 * @date 2019-03-04
 */
@Controller
@RequestMapping("/admin/pipe/ship")
public class ShipController extends BaseController {
    private String prefix = "pipe/ship";

    @Autowired
    private IShipService shipService;

    @RequiresPermissions("pipe:ship:view")
    @GetMapping()
    public String ship() {
        return prefix + "/ship";
    }

    /**
     * 查询号船列表
     */
    @RequiresPermissions("pipe:ship:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Ship ship) {
        startPage();
        List<Ship> list = shipService.selectShipList(ship);
        return getDataTable(list);
    }


    /**
     * 导出号船列表
     */
    @RequiresPermissions("pipe:ship:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ship ship) {
        List<Ship> list = shipService.selectShipList(ship);
        ExcelUtil<Ship> util = new ExcelUtil<Ship>(Ship.class);
        return util.exportExcel(list, "ship");
    }

    /**
     * 新增号船
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存号船
     */
    @RequiresPermissions("pipe:ship:add")
    @Log(title = "号船", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Ship ship) {
        return toAjax(shipService.insertShip(ship));
    }

    /**
     * 修改号船
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Ship ship = shipService.selectShipById(id);
        mmap.put("ship", ship);
        return prefix + "/edit";
    }

    /**
     * 修改保存号船
     */
    @RequiresPermissions("pipe:ship:edit")
    @Log(title = "号船", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Ship ship) {
        return toAjax(shipService.updateShip(ship));
    }

    /**
     * 删除号船
     */
    @RequiresPermissions("pipe:ship:remove")
    @Log(title = "号船", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(shipService.deleteShipByIds(ids));
    }

    /**
     * 获取船列表
     */
    @PostMapping("/selectShipSimpleList")
    @ResponseBody
    public List<ShipSimple> selectShipSimpleList(@PathVariable("isFinished") Integer isFinished) {
        if(isFinished!=null && isFinished<0){
            isFinished =null;
        }
        return shipService.selectShipSimpleList(isFinished);
    }
}
