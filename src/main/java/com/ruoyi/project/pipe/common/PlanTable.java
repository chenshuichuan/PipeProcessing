package com.ruoyi.project.pipe.common;

import com.ruoyi.project.pipe.cutPlan.domain.CutPlan;
import com.ruoyi.project.pipe.processPlan.domain.ProcessPlan;

import java.util.Date;

/**
/**
 *@ClassName: PlanTable
 *@Description: 解析的PlanTable 生成计划表
 *@Author: Ricardo
 *@Date: 2019/3/3 10:00
 **/
public class PlanTable {

    private CutPlan cutPlan;
    private ProcessPlan processPlan;

    /** 序号 */
    private String serialNumber;
    /** 上传的计划名称 */
    private String planName;
    /** 船名 */
    private String shipName;
    /** 加工批次 */
    private String batchName;
    /** 加工批次描述 */
    private String batchDescription;

    //下料完成时间 = 加工计划开始时间 - cutDelDateNumber
    private int cutDelDateNumber;
    //加工计划完成时间 = 加工计划开始时间 + cutDelDateNumber
    private int planEndDateNumber;

    public int getPlanEndDateNumber() {
        return planEndDateNumber;
    }

    public void setPlanEndDateNumber(int planEndDateNumber) {
        this.planEndDateNumber = planEndDateNumber;
    }

    public int getCutDelDateNumber() {
        return cutDelDateNumber;
    }

    public void setCutDelDateNumber(int cutDelDateNumber) {
        this.cutDelDateNumber = cutDelDateNumber;
    }

    public PlanTable() {
        cutDelDateNumber = 0;
        planEndDateNumber =0;
        cutPlan = new CutPlan();
        cutPlan.setIsArrange(0);
        cutPlan.setIsFinished(0);
        processPlan = new ProcessPlan();
        processPlan.setIsArrange(0);
        processPlan.setIsFinished(0);
        processPlan.setAnalysisStatus(0);
    }
    public PlanTable(CutPlan cutPlan, ProcessPlan processPlan) {
        this.cutPlan = cutPlan;
        this.processPlan = processPlan;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        cutPlan.setSerialNumber(serialNumber);
        processPlan.setSerialNumber(serialNumber);
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        cutPlan.setPlanName(planName);
        processPlan.setPlanName(planName);
        this.planName = planName;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        cutPlan.setShipName(shipName);
        processPlan.setShipName(shipName);
        this.shipName = shipName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        cutPlan.setBatchName(batchName);
        processPlan.setBatchName(batchName);
        this.batchName = batchName;
    }

    public String getBatchDescription() {
        return batchDescription;
    }

    public void setBatchDescription(String batchDescription) {
        this.batchDescription = batchDescription;
        cutPlan.setBatchDescription(batchDescription);
        processPlan.setBatchDescription(batchDescription);
    }


    public CutPlan getCutPlan() {
        return cutPlan;
    }

    public void setCutPlan(CutPlan cutPlan) {
        this.cutPlan = cutPlan;
    }

    public ProcessPlan getProcessPlan() {
        return processPlan;
    }

    public void setProcessPlan(ProcessPlan processPlan) {
        this.processPlan = processPlan;
    }

    @Override
    public String toString() {
        return "PlanTable{" +
                "cutPlan=" + cutPlan +
                ", processPlan=" + processPlan +
                ", serialNumber='" + serialNumber + '\'' +
                ", planName='" + planName + '\'' +
                ", shipName='" + shipName + '\'' +
                ", batchName='" + batchName + '\'' +
                ", batchDescription='" + batchDescription + '\'' +
                '}';
    }
}
