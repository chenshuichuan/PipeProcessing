package com.ruoyi.project.system.workplace.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.project.process.middleStatus.domain.MiddleStatus;
import com.ruoyi.project.process.middleStatus.service.MiddleStatusRepository;
import com.ruoyi.project.system.dept.domain.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.workplace.mapper.WorkplaceMapper;
import com.ruoyi.project.system.workplace.domain.Workplace;
import com.ruoyi.project.system.workplace.service.IWorkplaceService;
import com.ruoyi.common.support.Convert;

/**
 * 部门 服务层实现
 *
 * @author yc
 * @date 2019-02-28
 */
@Service
public class WorkplaceServiceImpl implements IWorkplaceService {
    @Autowired
    private WorkplaceMapper workplaceMapper;
    @Autowired
    private MiddleStatusRepository middleStatusRepository;

    /**
     * 查询部门信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public Workplace selectWorkplaceById(Integer deptId) {
        return workplaceMapper.selectWorkplaceById(deptId);
    }

    /**
     * 查询部门列表
     *
     * @param workplace 部门信息
     * @return 部门集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<Workplace> selectWorkplaceList(Workplace workplace) {
        return workplaceMapper.selectWorkplaceList(workplace);
    }

    /**
     * 新增部门
     *
     * @param workplace 部门信息
     * @return 结果
     */
    @Override
    public int insertWorkplace(Workplace workplace) {
        Workplace info = workplaceMapper.selectWorkplaceById(workplace.getParentId());
        workplace.setCreateBy(ShiroUtils.getLoginName());
        workplace.setAncestors(info.getAncestors() + "," + workplace.getParentId());
        return workplaceMapper.insertWorkplace(workplace);
    }

    /**
     * 修改部门
     *
     * @param workplace 部门信息
     * @return 结果
     */
    @Override
    public int updateWorkplace(Workplace workplace) {
        Workplace info = workplaceMapper.selectWorkplaceById(workplace.getParentId());
        if (StringUtils.isNotNull(info)) {
            String ancestors = info.getAncestors() + "," + workplace.getParentId();
            workplace.setAncestors(ancestors);
            updateDeptChildren(workplace.getDeptId(), ancestors);
        }
        //workplace.setUpdateBy(ShiroUtils.getLoginName());
        return workplaceMapper.updateWorkplace(workplace);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId    部门ID
     * @param ancestors 元素列表
     */
    public void updateDeptChildren(Integer deptId, String ancestors) {
        Workplace workplace = new Workplace();
        workplace.setParentId(deptId);
        List<Workplace> childrens = workplaceMapper.selectWorkplaceList(workplace);
        for (Workplace children : childrens) {
            children.setAncestors(ancestors + "," + workplace.getParentId());
        }
        if (childrens.size() > 0) {
            workplaceMapper.updateDeptChildren(childrens);
        }
    }


    /**
     * 删除部门对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWorkplaceByIds(String ids) {
        return workplaceMapper.deleteWorkplaceByIds(Convert.toStrArray(ids));
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkWorkplaceNameUnique(Workplace dept) {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        Workplace info = workplaceMapper.checkWorkplaceNameUnique(dept.getWorkplaceName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue()) {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    /**
     * 查询部门管理树
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<Map<String, Object>> selectWorkplaceTree(Workplace dept) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Workplace> deptList = workplaceMapper.selectWorkplaceList(dept);
        trees = getTrees(deptList, false, null);
        return trees;
    }
    /**
     * 查询派工工位树（按照父部门查询）
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<Map<String, Object>> selectArrangeTree(Workplace dept) {
        Workplace workplace = workplaceMapper.selectWorkplaceById(dept.getParentId());
        if(workplace == null){
            return null;
        }
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<Workplace> deptList = workplaceMapper.selectWorkplaceList(dept);
        //加上父部门
        deptList.add(workplace);
        trees = getArrangeTrees(deptList);
        return trees;
    }
    @Override
    public int deleteWorkplaceById(Integer deptId) {
        return workplaceMapper.deleteWorkplaceById(deptId);
    }

    @Override
    public Workplace selectByName(String workPlaceName) {
        Workplace workplace = new Workplace();
        workplace.setWorkplaceName(workPlaceName);
        List<Workplace> workplaceList = selectWorkplaceList(workplace);
        if(workplaceList.size() !=1){
            MiddleStatus middleStatus = new MiddleStatus("工位名称请保持唯一！","sys_workplace", workPlaceName,"selectByName");
            middleStatusRepository.save(middleStatus);
            if(workplaceList.size()>1){
                return workplaceList.get(0);
            }
        }
        else {
            return workplaceList.get(0);
        }
        return  null;
    }

    /**
     * 对象转部门树
     *
     * @param deptList     部门列表
     * @param isCheck      是否需要选中
     * @param roleDeptList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<Workplace> deptList, boolean isCheck, List<String> roleDeptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (Workplace dept : deptList) {

            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", dept.getDeptId());
            deptMap.put("pId", dept.getParentId());
            deptMap.put("name", dept.getWorkplaceName());
            deptMap.put("title", dept.getWorkplaceName());

//			if("9".equals(dept.getWorkplaceType())){
//				deptMap.put("iconSkin", "iconClose");
//			}
//			else{
//				deptMap.put("iconSkin", "iconOk");
//			}
//            if (isCheck) {
//                deptMap.put("checked", roleDeptList.contains(dept.getDeptId() + dept.getWorkplaceName()));
//            } else {
//                deptMap.put("checked", false);
//            }
            trees.add(deptMap);
        }
        return trees;
    }

    /**
     * 对象树
     * @param deptList     部门列表
     * @return
     */
    public List<Map<String, Object>> getArrangeTrees(List<Workplace> deptList) {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (Workplace dept : deptList) {

            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", dept.getDeptId());
            deptMap.put("pId", dept.getParentId());
            deptMap.put("title", dept.getWorkplaceName());
            if(!"9".equals(dept.getWorkplaceType()) && "0".equals(dept.getStatus())){
                deptMap.put("name", dept.getWorkplaceName()+"-空闲");
            }
            else{
                deptMap.put("name", dept.getWorkplaceName());
            }
            trees.add(deptMap);
        }
        return trees;
    }

}
