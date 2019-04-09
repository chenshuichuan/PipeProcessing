package com.ruoyi.project.process.batchArrange.domain;

/**
 * @ClassName: ArrangeInfo
 * @Description: 派工传输info
 * @Author: Ricardo
 * @Date: 2019/4/7 23:15
 **/
public class ArrangeInfo {
    /**
     * cutPlan 的id
     */
    private Integer id;
    /**
     * 该次计划下料总数
     */
    private Integer totalCutNumber;
    /**
     * 一部大管下料工段 的下料数
     */
    private Integer onebigCutNumber;
    /**
     * 一部下料工段 的下料数
     */
    private Integer oneTotalNumber;
    /**
     * 二部下料工段 的下料数
     */
    private Integer twoTotalNumber;

    /**
     * 一部大管下料工段 的 工位id
     */
    private Integer onebigCutId;
    /**
     * 一部下料工段 的 工位id
     */
    private Integer oneCutId;

    /**
     * 二部下料工段 的 工位id
     */
    private Integer twoCutId;

    public ArrangeInfo() {
    }

    public ArrangeInfo(Integer id, Integer totalCutNumber, Integer onebigCutNumber,
                       Integer oneTotalNumber, Integer twoTotalNumber, Integer onebigCutId,
                       Integer oneCutId, Integer twoCutId) {
        this.id = id;
        this.totalCutNumber = totalCutNumber;
        this.onebigCutNumber = onebigCutNumber;
        this.oneTotalNumber = oneTotalNumber;
        this.twoTotalNumber = twoTotalNumber;
        this.onebigCutId = onebigCutId;
        this.oneCutId = oneCutId;
        this.twoCutId = twoCutId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalCutNumber() {
        return totalCutNumber;
    }

    public void setTotalCutNumber(Integer totalCutNumber) {
        this.totalCutNumber = totalCutNumber;
    }

    public Integer getOnebigCutNumber() {
        return onebigCutNumber;
    }

    public void setOnebigCutNumber(Integer onebigCutNumber) {
        this.onebigCutNumber = onebigCutNumber;
    }



    public Integer getOnebigCutId() {
        return onebigCutId;
    }

    public void setOnebigCutId(Integer onebigCutId) {
        this.onebigCutId = onebigCutId;
    }

    public Integer getOneCutId() {
        return oneCutId;
    }

    public void setOneCutId(Integer oneCutId) {
        this.oneCutId = oneCutId;
    }

    public Integer getTwoCutId() {
        return twoCutId;
    }

    public void setTwoCutId(Integer twoCutId) {
        this.twoCutId = twoCutId;
    }

    @Override
    public String toString() {
        return "ArrangeInfo{" +
                "id=" + id +
                ", totalCutNumber=" + totalCutNumber +
                ", onebigCutNumber=" + onebigCutNumber +
                ", oneTotalNumber=" + oneTotalNumber +
                ", twoTotalNumber=" + twoTotalNumber +
                ", onebigCutId=" + onebigCutId +
                ", oneCutId=" + oneCutId +
                ", twoCutId=" + twoCutId +
                '}';
    }

    public Integer getOneTotalNumber() {
        return oneTotalNumber;
    }

    public void setOneTotalNumber(Integer oneTotalNumber) {
        this.oneTotalNumber = oneTotalNumber;
    }

    public Integer getTwoTotalNumber() {
        return twoTotalNumber;
    }

    public void setTwoTotalNumber(Integer twoTotalNumber) {
        this.twoTotalNumber = twoTotalNumber;
    }
}
