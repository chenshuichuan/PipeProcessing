package com.ruoyi.project.process.taoliaoOnline.domain;

import com.ruoyi.project.process.taoliao.domain.Taoliao;

import java.util.List;

/**
 *@ClassName: InputOriginInfo
 *@Description: 用户输入的套料原材料信息
 *@Author: Ricardo
 *@Date: 2019/4/22 22:15
 **/
public class InputOriginId {
    private int id;
    private List<OriginInfo> originInfoList;

    public InputOriginId(int id, List<OriginInfo> originInfoList) {
        this.id = id;
        this.originInfoList = originInfoList;
    }
    public InputOriginId() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OriginInfo> getOriginInfoList() {
        return originInfoList;
    }

    public void setOriginInfoList(List<OriginInfo> originInfoList) {
        this.originInfoList = originInfoList;
    }


}
