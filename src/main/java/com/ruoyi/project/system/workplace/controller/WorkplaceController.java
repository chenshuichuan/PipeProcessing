package com.ruoyi.project.system.workplace.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import io.swagger.models.auth.In;
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
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 部门 信息操作处理
 * 
 * @author yc
 * @date 2019-02-28
 */
@Controller
@RequestMapping("/admin/system/workplace")
public class WorkplaceController extends BaseController
{
    private String prefix = "system/workplace";
	
	@Autowired
	private IWorkplaceService workplaceService;
	
	@RequiresPermissions("system:workplace:view")
	@GetMapping()
	public String workplace()
	{
	    return prefix + "/workplace";
	}
	
	/**
	 * 查询部门列表
	 */
	@RequiresPermissions("system:workplace:list")
	@GetMapping("/list")
	@ResponseBody
	public List<Workplace> list(Workplace workplace)
	{
//		startPage();
        List<Workplace> list = workplaceService.selectWorkplaceList(workplace);
		return list;
	}
	
	
	/**
	 * 导出部门列表
	 */
	@RequiresPermissions("system:workplace:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Workplace workplace)
    {
    	List<Workplace> list = workplaceService.selectWorkplaceList(workplace);
        ExcelUtil<Workplace> util = new ExcelUtil<Workplace>(Workplace.class);
        return util.exportExcel(list, "workplace");
    }
	
	/**
	 * 新增部门
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	/**
	 * 新增部门
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Integer parentId, ModelMap mmap)
	{
		mmap.put("dept", workplaceService.selectWorkplaceById(parentId));
		return prefix + "/add";
	}


	/**
	 * 新增保存部门
	 */
	@RequiresPermissions("system:workplace:add")
	@Log(title = "部门", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Workplace workplace)
	{		
		return toAjax(workplaceService.insertWorkplace(workplace));
	}

	/**
	 * 修改部门
	 */
	@GetMapping("/edit/{deptId}")
	public String edit(@PathVariable("deptId") Integer deptId, ModelMap mmap)
	{

		Workplace workplace = workplaceService.selectWorkplaceById(deptId);
		if (StringUtils.isNotNull(workplace) && 100L == deptId)
		{
			workplace.setParentName("无");
		}
		mmap.put("workplace", workplace);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存部门
	 */
	@RequiresPermissions("system:workplace:edit")
	@Log(title = "部门", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Workplace workplace)
	{		
		return toAjax(workplaceService.updateWorkplace(workplace));
	}
	
	/**
	 * 删除部门
	 */
	@RequiresPermissions("system:workplace:remove")
	@Log(title = "部门", businessType = BusinessType.DELETE)
	@PostMapping("/remove/{deptId}")
	@ResponseBody
	public AjaxResult remove(@PathVariable("deptId") Integer deptId)
	{		
		return toAjax(workplaceService.deleteWorkplaceById(deptId));
	}

	/**
	 * 校验工位结构名称
	 */
	@PostMapping("/checkWorkplaceNameUnique")
	@ResponseBody
	public String checkWorkplaceNameUnique(Workplace dept)
	{
		return workplaceService.checkWorkplaceNameUnique(dept);
	}

	/**
	 * 选择工位结构树
	 */
	@GetMapping("/selectWorkplaceTree/{workplaceId}")
	public String selectWorkplaceTree(@PathVariable("workplaceId") Integer deptId, ModelMap mmap)
	{
		mmap.put("workplace", workplaceService.selectWorkplaceById(deptId));
		return prefix + "/tree";
	}

	/**
	 * 加载工位结构列表树
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData()
	{
		List<Map<String, Object>> tree = workplaceService.selectWorkplaceTree(new Workplace());
		return tree;
	}
}
