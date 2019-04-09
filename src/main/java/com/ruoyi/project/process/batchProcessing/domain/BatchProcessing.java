package com.ruoyi.project.process.batchProcessing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 批次加工表 process_batch_processing
 *
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name = "process_batch_processing")
public class BatchProcessing extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 批次加工序号，对应cut_plan表格的数据
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 批次_id
     */
    @Column(name = "batch_id")
    private Integer batchId;
    /**
     * 包含管子数
     */
    @Column(name = "pipe_number")
    private Integer pipeNumber;
    /**
     * 包含单元数
     */
    @Column(name = "unit_number")
    private Integer unitNumber;
    /**
     * 加工中单元数
     */
    @Column(name = "unit_processing_number")
    private Integer unitProcessingNumber;
    /**
     * 加工完成单元数
     */
    @Column(name = "unit_finished_number")
    private Integer unitFinishedNumber;
    /**
     * 加工中管件
     */
    @Column(name = "pipe_processing_number")
    private Integer pipeProcessingNumber;
    /**
     * 加工完成管件
     */
    @Column(name = "pipe_finished_number")
    private Integer pipeFinishedNumber;
    /**
     * 下料派工记录id
     */
    @Column(name = "arrange_id")
    private Integer arrangeId;
    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 是否完工
     */
    @Column(name = "is_finished")
    private Integer isFinished;
    public Integer getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }


    public BatchProcessing() {
    }

    public BatchProcessing(Integer batchId, Integer pipeNumber, Integer unitNumber, Integer unitProcessingNumber, Integer unitFinishedNumber,
                           Integer pipeProcessingNumber, Integer pipeFinishedNumber, Integer arrangeId, Date updateTime, Integer isFinished) {
        this.batchId = batchId;
        this.pipeNumber = pipeNumber;
        this.unitNumber = unitNumber;
        this.unitProcessingNumber = unitProcessingNumber;
        this.unitFinishedNumber = unitFinishedNumber;
        this.pipeProcessingNumber = pipeProcessingNumber;
        this.pipeFinishedNumber = pipeFinishedNumber;
        this.arrangeId = arrangeId;
        this.updateTime = updateTime;
        this.isFinished = isFinished;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setPipeNumber(Integer pipeNumber) {
        this.pipeNumber = pipeNumber;
    }

    public Integer getPipeNumber() {
        return pipeNumber;
    }

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public void setUnitProcessingNumber(Integer unitProcessingNumber) {
        this.unitProcessingNumber = unitProcessingNumber;
    }

    public Integer getUnitProcessingNumber() {
        return unitProcessingNumber;
    }

    public void setUnitFinishedNumber(Integer unitFinishedNumber) {
        this.unitFinishedNumber = unitFinishedNumber;
    }

    public Integer getUnitFinishedNumber() {
        return unitFinishedNumber;
    }

    public void setPipeProcessingNumber(Integer pipeProcessingNumber) {
        this.pipeProcessingNumber = pipeProcessingNumber;
    }

    public Integer getPipeProcessingNumber() {
        return pipeProcessingNumber;
    }

    public void setPipeFinishedNumber(Integer pipeFinishedNumber) {
        this.pipeFinishedNumber = pipeFinishedNumber;
    }

    public Integer getPipeFinishedNumber() {
        return pipeFinishedNumber;
    }

    public void setArrangeId(Integer arrangeId) {
        this.arrangeId = arrangeId;
    }

    public Integer getArrangeId() {
        return arrangeId;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("batchId", getBatchId())
                .append("pipeNumber", getPipeNumber())
                .append("unitNumber", getUnitNumber())
                .append("unitProcessingNumber", getUnitProcessingNumber())
                .append("unitFinishedNumber", getUnitFinishedNumber())
                .append("pipeProcessingNumber", getPipeProcessingNumber())
                .append("pipeFinishedNumber", getPipeFinishedNumber())
                .append("arrangeId", getArrangeId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
