package com.ruoyi.project.system.workplace.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 部门表 sys_workplace
 * 
 * @author yc
 * @date 2019-02-28
 */
public class Workplace extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 部门id */
	private Integer deptId;
	/** 父部门id */
	private Integer parentId;
	/** 祖级列表 */
	private String ancestors;
	/** 工位类型 */
	private String workplaceType;
	/** 工位名称 */
	private String workplaceName;
	/** 工位码 */
	private String workplaceCode;
	/** 显示顺序 */
	private Integer orderNum;
	/** 工位状态（0正常 1停用） */
	private String status;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;

	public void setDeptId(Integer deptId) 
	{
		this.deptId = deptId;
	}

	public Integer getDeptId() 
	{
		return deptId;
	}
	public void setParentId(Integer parentId) 
	{
		this.parentId = parentId;
	}

	public Integer getParentId() 
	{
		return parentId;
	}
	public void setAncestors(String ancestors) 
	{
		this.ancestors = ancestors;
	}

	public String getAncestors() 
	{
		return ancestors;
	}
	public void setWorkplaceType(String workplaceType) 
	{
		this.workplaceType = workplaceType;
	}

	public String getWorkplaceType() 
	{
		return workplaceType;
	}
	public void setWorkplaceName(String workplaceName) 
	{
		this.workplaceName = workplaceName;
	}

	public String getWorkplaceName() 
	{
		return workplaceName;
	}
	public void setWorkplaceCode(String workplaceCode) 
	{
		this.workplaceCode = workplaceCode;
	}

	public String getWorkplaceCode() 
	{
		return workplaceCode;
	}
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}

	public Integer getOrderNum() 
	{
		return orderNum;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("workplaceType", getWorkplaceType())
            .append("workplaceName", getWorkplaceName())
            .append("workplaceCode", getWorkplaceCode())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
