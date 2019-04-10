package com.ruoyi.project.process.unitArrange.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.pipe.unit.domain.Unit;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 加工单元表 pipe_unit
 *
 * @author ricardo
 * @date 2019-03-04
 */

public class UnitArrangeInfo  {
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
     * 批次id
     */
    private Integer batchId;
    /**
     * 批次名称
     */
    private String batchName;

    /**
     * 加工顺序:下料-》弯管
     */
    private String processOrder;
    /**
     * 当前所在工序
     */
    private String processStage;
    /**
     * 下一工序
     */
    private String nextStage;

    /**
     * 是否完工
     */
    private Integer isFinished;
    /**
     * 当前工序是否已完工
     * 用于决定是否可以派工到下一工序
     */
    private Integer isArrange;

    /**派工工位Id
     */
    private Integer workPlaceId;

    /**
     * 加工工段id
     */
    private Integer processSectionId;
    /**
     * 加工工段
     */
    private String processSection;

    public UnitArrangeInfo() {
    }
    public UnitArrangeInfo(Unit unit, String processOrder,String processStage, String nextStage, Integer workPlaceId, Integer processSectionId) {
        this.id = unit.getId();
        this.name = unit.getName();
        this.shipCode = unit.getShipCode();
        this.batchId = unit.getBatchId();
        this.batchName = unit.getBatchName();
        this.processSection = unit.getProcessSection();
        this.isFinished = unit.getIsFinished();
        this.isArrange = unit.getIsFinished();

        this.processOrder =processOrder;
        this.processStage = processStage;
        this.nextStage = nextStage;
        this.workPlaceId = workPlaceId;
        this.processSectionId = processSectionId;
    }

    public UnitArrangeInfo(Integer id, String name, String shipCode, Integer batchId, String batchName, String processOrder, String processStage,
                           String nextStage, Integer isFinished, Integer isArrange, Integer workPlaceId,
                           Integer processSectionId, String processSection) {
        this.id = id;
        this.name = name;
        this.shipCode = shipCode;
        this.batchId = batchId;
        this.batchName = batchName;
        this.processOrder = processOrder;
        this.processStage = processStage;
        this.nextStage = nextStage;
        this.isFinished = isFinished;
        this.isArrange = isArrange;
        this.workPlaceId = workPlaceId;
        this.processSectionId = processSectionId;
        this.processSection = processSection;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getProcessOrder() {
        return processOrder;
    }

    public void setProcessOrder(String processOrder) {
        this.processOrder = processOrder;
    }

    public String getProcessStage() {
        return processStage;
    }

    public void setProcessStage(String processStage) {
        this.processStage = processStage;
    }

    public String getNextStage() {
        return nextStage;
    }

    public void setNextStage(String nextStage) {
        this.nextStage = nextStage;
    }

    public Integer getWorkPlaceId() {
        return workPlaceId;
    }

    public void setWorkPlaceId(Integer workPlaceId) {
        this.workPlaceId = workPlaceId;
    }

    public Integer getProcessSectionId() {
        return processSectionId;
    }

    public void setProcessSectionId(Integer processSectionId) {
        this.processSectionId = processSectionId;
    }

    public void setProcessSection(String processSection) {
        this.processSection = processSection;
    }

    public String getProcessSection() {
        return processSection;
    }


    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return "UnitArrangeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shipCode='" + shipCode + '\'' +
                ", batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", processOrder='" + processOrder + '\'' +
                ", processStage='" + processStage + '\'' +
                ", nextStage='" + nextStage + '\'' +
                ", isFinished=" + isFinished +
                ", isArrange=" + isArrange +
                ", workPlaceId=" + workPlaceId +
                ", processSectionId=" + processSectionId +
                ", processSection='" + processSection + '\'' +
                '}';
    }
}
