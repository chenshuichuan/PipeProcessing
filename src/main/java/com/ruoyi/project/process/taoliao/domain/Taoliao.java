package com.ruoyi.project.process.taoliao.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 套料管材表 process_taoliao
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name="process_taoliao")
public class Taoliao extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	@Id
	@GeneratedValue
    @Column(name = "id")
	private Integer id;
	/** 对应的计划id */
    @Column(name = "plan_id")
	private Integer planId;
	/** 批次id */
    @Column(name = "batch_id")
	private Integer batchId;
	/** 管材 */
    @Column(name = "pipe_material")
	private String pipeMaterial;
	/** 所有管总长度 */
    @Column(name = "total_length")
	private Integer totalLength;
	/** 包含管数量 */
    @Column(name = "pipe_number")
	private Integer pipeNumber;
	/** 是否已经套过料了 */
    @Column(name = "is_taoliao")
	private Integer isTaoliao;
	/** 更新时间 */
    @Column(name = "update_time")
	private Date updateTime;

	public Taoliao() {
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPlanId(Integer planId) 
	{
		this.planId = planId;
	}

	public Integer getPlanId() 
	{
		return planId;
	}
	public void setBatchId(Integer batchId) 
	{
		this.batchId = batchId;
	}

	public Integer getBatchId() 
	{
		return batchId;
	}
	public void setPipeMaterial(String pipeMaterial) 
	{
		this.pipeMaterial = pipeMaterial;
	}

	public String getPipeMaterial() 
	{
		return pipeMaterial;
	}
	public void setTotalLength(Integer totalLength) 
	{
		this.totalLength = totalLength;
	}

	public Integer getTotalLength() 
	{
		return totalLength;
	}
	public void setPipeNumber(Integer pipeNumber) 
	{
		this.pipeNumber = pipeNumber;
	}

	public Integer getPipeNumber() 
	{
		return pipeNumber;
	}
	public void setIsTaoliao(Integer isTaoliao) 
	{
		this.isTaoliao = isTaoliao;
	}

	public Integer getIsTaoliao() 
	{
		return isTaoliao;
	}
	@Override
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	@Override
	public Date getUpdateTime() 
	{
		return updateTime;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planId", getPlanId())
            .append("batchId", getBatchId())
            .append("pipeMaterial", getPipeMaterial())
            .append("totalLength", getTotalLength())
            .append("pipeNumber", getPipeNumber())
            .append("isTaoliao", getIsTaoliao())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
