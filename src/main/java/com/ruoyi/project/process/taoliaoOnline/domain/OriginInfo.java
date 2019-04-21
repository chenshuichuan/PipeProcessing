package com.ruoyi.project.process.taoliaoOnline.domain;

/**
 *@ClassName: OriginInfo
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/4/21 12:54
 **/
public class OriginInfo {
    private int originLength;
    private int originNumber;

    public OriginInfo(int originLength, int originNumber) {
        this.originLength = originLength;
        this.originNumber = originNumber;
    }
    public OriginInfo() {
        this.originLength = 0;
        this.originNumber = 0;
    }
    public int getOriginLength() {
        return originLength;
    }

    public void setOriginLength(int originLength) {
        this.originLength = originLength;
    }

    public int getOriginNumber() {
        return originNumber;
    }

    public void setOriginNumber(int originNumber) {
        this.originNumber = originNumber;
    }
}
