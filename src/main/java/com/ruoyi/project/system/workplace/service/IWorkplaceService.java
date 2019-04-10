package com.ruoyi.project.system.workplace.service;

import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.system.workplace.domain.Workplace;

import java.util.List;
import java.util.Map;

/**
 * 部门 服务层
 *
 * @author yc
 * @date 2019-02-28
 */
public interface IWorkplaceService {
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
     * 删除部门信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWorkplaceByIds(String ids);

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    public String checkWorkplaceNameUnique(Workplace dept);

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Map<String, Object>> selectWorkplaceTree(Workplace dept);

    /**
     * 查询派工工位树（按照父部门查询）
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Map<String, Object>> selectArrangeTree(Workplace dept);

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int deleteWorkplaceById(Integer deptId);

    Workplace selectByName(String WorkPlaceName);

}
