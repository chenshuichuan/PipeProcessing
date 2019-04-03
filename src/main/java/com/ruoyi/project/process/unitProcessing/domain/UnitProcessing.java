package com.ruoyi.project.process.unitProcessing.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * 单元加工记录表 process_unit_processing
 *
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name = "process_unit_processing")
public class UnitProcessing extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 记录id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 单元pipe_unit 的id
     */
    @Column(name = "unit_id")
    private Integer unitId;
    /**
     * 所属批次
     */
    @Column(name = "batch_id")
    private Integer batchId;
    /**
     * 当前工序
     */
    @Column(name = "process_stage")
    private Integer processStage;
    /**
     * 当前工序排几，从下料算1开始算
     */
    @Column(name = "process_index")
    private Integer processIndex;
    /**
     * 加工中管数量
     */
    @Column(name = "pipe_processing_number")
    private Integer pipeProcessingNumber;
    /**
     * 加工完成管数量
     */
    @Column(name = "pipe_finished_number")
    private Integer pipeFinishedNumber;
    /**
     * 单元派工id
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

    public UnitProcessing() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setProcessStage(Integer processStage) {
        this.processStage = processStage;
    }

    public Integer getProcessStage() {
        return processStage;
    }

    public void setProcessIndex(Integer processIndex) {
        this.processIndex = processIndex;
    }

    public Integer getProcessIndex() {
        return processIndex;
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
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("unitId", getUnitId())
                .append("batchId", getBatchId())
                .append("processStage", getProcessStage())
                .append("processIndex", getProcessIndex())
                .append("pipeProcessingNumber", getPipeProcessingNumber())
                .append("pipeFinishedNumber", getPipeFinishedNumber())
                .append("arrangeId", getArrangeId())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
