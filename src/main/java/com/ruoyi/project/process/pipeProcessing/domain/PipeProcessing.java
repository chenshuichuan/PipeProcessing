package com.ruoyi.project.process.pipeProcessing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 管件加工表 process_pipe_processing
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name="process_pipe_processing")
public class PipeProcessing extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
	@Id
	@GeneratedValue
    @Column(name = "id")
	private Integer id;
	/** 对应pipe_pipe的管id */
    @Column(name = "pipe_id")
	private Integer pipeId;
	/** 当前工序 */
    @Column(name = "process_stage")
	private Integer processStage;
	/** 当前工序排几 */
    @Column(name = "process_index")
	private Integer processIndex;
	/** 派工单id */
    @Column(name = "arrange_id")
	private Integer arrangeId;
	/** 更新时间 */
    @Column(name = "update_time")
	private Date updateTime;
	/** 所属批次 */
    @Column(name = "batch_id")
	private Integer batchId;
	/** 所属单元 */
    @Column(name = "unit_id")
	private Integer unitId;

	public PipeProcessing() {
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setPipeId(Integer pipeId) 
	{
		this.pipeId = pipeId;
	}

	public Integer getPipeId() 
	{
		return pipeId;
	}
	public void setProcessStage(Integer processStage) 
	{
		this.processStage = processStage;
	}

	public Integer getProcessStage() 
	{
		return processStage;
	}
	public void setProcessIndex(Integer processIndex) 
	{
		this.processIndex = processIndex;
	}

	public Integer getProcessIndex() 
	{
		return processIndex;
	}
	public void setArrangeId(Integer arrangeId) 
	{
		this.arrangeId = arrangeId;
	}

	public Integer getArrangeId() 
	{
		return arrangeId;
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
	public void setBatchId(Integer batchId) 
	{
		this.batchId = batchId;
	}

	public Integer getBatchId() 
	{
		return batchId;
	}
	public void setUnitId(Integer unitId) 
	{
		this.unitId = unitId;
	}

	public Integer getUnitId() 
	{
		return unitId;
	}
	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pipeId", getPipeId())
            .append("processStage", getProcessStage())
            .append("processIndex", getProcessIndex())
            .append("arrangeId", getArrangeId())
            .append("updateTime", getUpdateTime())
            .append("batchId", getBatchId())
            .append("unitId", getUnitId())
            .toString();
    }
}
