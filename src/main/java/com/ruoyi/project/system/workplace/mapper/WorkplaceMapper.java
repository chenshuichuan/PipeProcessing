package com.ruoyi.project.system.workplace.mapper;

import com.ruoyi.project.system.workplace.domain.Workplace;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门 数据层
 * 
 * @author yc
 * @date 2019-02-28
 */
public interface WorkplaceMapper 
{
	/**
     * 查询部门信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
	public Workplace selectWorkplaceById(Integer deptId);
	
	/**
     * 查询部门列表
     * 
     * @param workplace 部门信息
     * @return 部门集合
     */
	public List<Workplace> selectWorkplaceList(Workplace workplace);
	
	/**
     * 新增部门
     * 
     * @param workplace 部门信息
     * @return 结果
     */
	public int insertWorkplace(Workplace workplace);
	
	/**
     * 修改部门
     * 
     * @param workplace 部门信息
     * @return 结果
     */
	public int updateWorkplace(Workplace workplace);
	
	/**
     * 删除部门
     * 
     * @param deptId 部门ID
     * @return 结果
     */
	public int deleteWorkplaceById(Integer deptId);
	
	/**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteWorkplaceByIds(String[] deptIds);

	/**
	 * 校验部门名称是否唯一
	 *
	 * @param workpalceName 部门名称
	 * @param parentId 父部门ID
	 * @return 结果
	 */
	public Workplace checkWorkplaceNameUnique(@Param("workplaceName") String workpalceName, @Param("parentId") Integer parentId);

	/**
	 * 修改子元素关系
	 *
	 * @param workplaces 子元素
	 * @return 结果
	 */
	public int updateDeptChildren(@Param("workplaces") List<Workplace> workplaces);
}