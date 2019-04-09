package com.ruoyi.project.process.order.domain;

/**
 *@ClassName: ProcessStage
 *@Description: 加工工序
 *@Author: Ricardo
 *@Date: 2019/4/9 11:41
 **/
public class ProcessStage {
    private Integer orderId;

    private Integer stage;
    private String stageName;
    private Integer stageIndexOfOrder;

    public ProcessStage(Integer orderId, Integer stage, String stageName, Integer stageIndexOfOrder) {
        this.orderId = orderId;
        this.stage = stage;
        this.stageName = stageName;
        this.stageIndexOfOrder = stageIndexOfOrder;
    }

    public ProcessStage() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getStageIndexOfOrder() {
        return stageIndexOfOrder;
    }

    public void setStageIndexOfOrder(Integer stageIndexOfOrder) {
        this.stageIndexOfOrder = stageIndexOfOrder;
    }
}
