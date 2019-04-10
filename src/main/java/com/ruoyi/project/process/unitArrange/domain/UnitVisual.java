package com.ruoyi.project.process.unitArrange.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.pipe.unit.domain.Unit;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 加工单元表 pipe_unit
 *
 * @author ricardo
 * @date 2019-03-04
 */

public class UnitVisual extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 单元名称
     */
    private String name;
    /**
     * 号船代号
     */
    private String shipCode;
    /**
     * p批次id
     */
    private Integer batchId;
    /**
     * 批次名称
     */
    private String batchName;
    /**
     * 计划id-process_id
     */

    private Integer planId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 管形态
     */
    private String pipeShape;
    /**
     * 包含管件数
     */
    private Integer pipeNumber;
    /**
     * 下料工段
     */
    private String cutSection;
    /**
     * 加工工段
     */

    private String processSection;
    /**
     * 加工顺序:下料-》弯管
     */
    private String processOrderId;
    /**
     * 当前所在工序id
     */
    private Integer processStageId;
    /**
     * 下一工序id
     */
    private Integer nextStageId;
    /**
     * 未加工管数量
     */
    private Integer unprocessNumber;
    /**
     * j加工中管数
     */
    private Integer processingNumber;
    /**
     * 加工完成管数量
     */
    private Integer processedNumber;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否完工
     */
    private Integer isFinished;
    /**
     * 当前工序是否已完工
     * 用于决定是否可以派工到下一工序
     */
    private Integer isArrange;

    public UnitVisual() {
    }
    public UnitVisual(Unit unit,String processOrderId) {
        this.id = unit.getId();
        this.name = unit.getName();
        this.shipCode = unit.getShipCode();
        this.batchId = unit.getBatchId();
        this.batchName = unit.getBatchName();
        this.planId = unit.getPlanId();
        this.remark = unit.getRemark();
        this.pipeShape = unit.getPipeShape();
        this.pipeNumber = unit.getPipeNumber();
        this.cutSection = unit.getCutSection();
        this.processSection = unit.getProcessSection();

        this.processOrderId = processOrderId;

        this.processStageId = unit.getProcessStageId();
        this.nextStageId = unit.getNextStageId();
        this.unprocessNumber = unit.getUnprocessNumber();
        this.processingNumber = unit.getProcessingNumber();
        this.processedNumber = unit.getProcessedNumber();
        this.updateTime = unit.getUpdateTime();
        this.isFinished = unit.getIsFinished();
        this.isArrange = unit.getIsFinished();
    }
    public UnitVisual(Integer id, String name, String shipCode, Integer batchId, String batchName,
                      Integer planId, String remark, String pipeShape,  String cutSection, String processSection,
                       Integer processStageId, Integer nextStageId,
                      Integer unprocessNumber, Integer processingNumber,  Date updateTime,
                      Integer isFinished, Integer isArrange) {
        this.id = id;
        this.name = name;
        this.shipCode = shipCode;
        this.batchId = batchId;
        this.batchName = batchName;
        this.planId = planId;
        this.remark = remark;
        this.pipeShape = pipeShape;
        this.cutSection = cutSection;
        this.processSection = processSection;
        this.processStageId = processStageId;
        this.nextStageId = nextStageId;
        this.unprocessNumber = unprocessNumber;
        this.processingNumber = processingNumber;
        this.updateTime = updateTime;
        this.isFinished = isFinished;
        this.isArrange = isArrange;
    }

    public Integer getIsArrange() {
        return isArrange;
    }
    public void setIsArrange(Integer isArrange) {
        this.isArrange = isArrange;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setShipCode(String shipCode) {
        this.shipCode = shipCode;
    }

    public String getShipCode() {
        return shipCode;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    public void setPipeShape(String pipeShape) {
        this.pipeShape = pipeShape;
    }

    public String getPipeShape() {
        return pipeShape;
    }

    public void setPipeNumber(Integer pipeNumber) {
        this.pipeNumber = pipeNumber;
    }

    public Integer getPipeNumber() {
        return pipeNumber;
    }

    public void setCutSection(String cutSection) {
        this.cutSection = cutSection;
    }

    public String getCutSection() {
        return cutSection;
    }

    public void setProcessSection(String processSection) {
        this.processSection = processSection;
    }

    public String getProcessSection() {
        return processSection;
    }

    public void setProcessOrderId(String processOrderId) {
        this.processOrderId = processOrderId;
    }

    public String getProcessOrderId() {
        return processOrderId;
    }

    public void setProcessStageId(Integer processStageId) {
        this.processStageId = processStageId;
    }

    public Integer getProcessStageId() {
        return processStageId;
    }

    public void setNextStageId(Integer nextStageId) {
        this.nextStageId = nextStageId;
    }

    public Integer getNextStageId() {
        return nextStageId;
    }

    public void setUnprocessNumber(Integer unprocessNumber) {
        this.unprocessNumber = unprocessNumber;
    }

    public Integer getUnprocessNumber() {
        return unprocessNumber;
    }

    public void setProcessingNumber(Integer processingNumber) {
        this.processingNumber = processingNumber;
    }

    public Integer getProcessingNumber() {
        return processingNumber;
    }

    public void setProcessedNumber(Integer processedNumber) {
        this.processedNumber = processedNumber;
    }

    public Integer getProcessedNumber() {
        return processedNumber;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("shipCode", getShipCode())
                .append("batchId", getBatchId())
                .append("batchName", getBatchName())
                .append("planId", getPlanId())
                .append("remark", getRemark())
                .append("pipeShape", getPipeShape())
                .append("pipeNumber", getPipeNumber())
                .append("cutSection", getCutSection())
                .append("processSection", getProcessSection())
                .append("processOrderId", getProcessOrderId())
                .append("processStageId", getProcessStageId())
                .append("nextStageId", getNextStageId())
                .append("unprocessNumber", getUnprocessNumber())
                .append("processingNumber", getProcessingNumber())
                .append("processedNumber", getProcessedNumber())
                .append("updateTime", getUpdateTime())
                .append("isFinished", getIsFinished())
                .append("isArrange", getIsArrange())
                .toString();
    }
}
