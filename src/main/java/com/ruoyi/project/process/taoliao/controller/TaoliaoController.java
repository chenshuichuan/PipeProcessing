package com.ruoyi.project.process.taoliao.controller;

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
import com.ruoyi.project.process.taoliao.domain.Taoliao;
import com.ruoyi.project.process.taoliao.service.ITaoliaoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 套料管材 信息操作处理
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Controller
@RequestMapping("/admin/process/taoliao")
public class TaoliaoController extends BaseController
{
    private String prefix = "process/taoliao";
	
	@Autowired
	private ITaoliaoService taoliaoService;
	
	@RequiresPermissions("process:taoliao:view")
	@GetMapping()
	public String taoliao()
	{
	    return prefix + "/taoliao";
	}
	
	/**
	 * 查询套料管材列表
	 */
	@RequiresPermissions("process:taoliao:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Taoliao taoliao)
	{
		startPage();
        List<Taoliao> list = taoliaoService.selectTaoliaoList(taoliao);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出套料管材列表
	 */
	@RequiresPermissions("process:taoliao:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Taoliao taoliao)
    {
    	List<Taoliao> list = taoliaoService.selectTaoliaoList(taoliao);
        ExcelUtil<Taoliao> util = new ExcelUtil<Taoliao>(Taoliao.class);
        return util.exportExcel(list, "taoliao");
    }
	
	/**
	 * 新增套料管材
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存套料管材
	 */
	@RequiresPermissions("process:taoliao:add")
	@Log(title = "套料管材", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Taoliao taoliao)
	{		
		return toAjax(taoliaoService.insertTaoliao(taoliao));
	}

	/**
	 * 修改套料管材
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Taoliao taoliao = taoliaoService.selectTaoliaoById(id);
		mmap.put("taoliao", taoliao);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存套料管材
	 */
	@RequiresPermissions("process:taoliao:edit")
	@Log(title = "套料管材", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Taoliao taoliao)
	{		
		return toAjax(taoliaoService.updateTaoliao(taoliao));
	}
	
	/**
	 * 删除套料管材
	 */
	@RequiresPermissions("process:taoliao:remove")
	@Log(title = "套料管材", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(taoliaoService.deleteTaoliaoByIds(ids));
	}
	
}
