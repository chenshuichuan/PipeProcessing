package com.ruoyi.project.process.taoliaoOnline.domain;

/**
 *@ClassName: OriginInfo
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/4/21 12:54
 **/
public class OriginInfo {
    private  int id;//前端使用
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "OriginInfo{" +
                "id=" + id +
                ", originLength=" + originLength +
                ", originNumber=" + originNumber +
                '}';
    }
}
