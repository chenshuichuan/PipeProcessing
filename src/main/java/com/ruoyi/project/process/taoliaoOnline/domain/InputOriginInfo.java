package com.ruoyi.project.process.taoliaoOnline.domain;

import com.ruoyi.project.process.taoliao.domain.Taoliao;

import java.util.List;

/**
 *@ClassName: InputOriginInfo
 *@Description: 用户输入的套料原材料信息
 *@Author: Ricardo
 *@Date: 2019/4/22 22:15
 **/
public class InputOriginInfo {
    private Taoliao taoliao;
    private List<OriginInfo> originInfoList;

    public InputOriginInfo(Taoliao taoliao, List<OriginInfo> originInfoList) {
        this.taoliao = taoliao;
        this.originInfoList = originInfoList;
    }
    public InputOriginInfo() {

    }
    public Taoliao getTaoliao() {
        return taoliao;
    }

    public void setTaoliao(Taoliao taoliao) {
        this.taoliao = taoliao;
    }

    public List<OriginInfo> getOriginInfoList() {
        return originInfoList;
    }

    public void setOriginInfoList(List<OriginInfo> originInfoList) {
        this.originInfoList = originInfoList;
    }

    @Override
    public String toString() {
        return "InputOriginInfo{" +
                "taoliao=" + taoliao +
                ", originInfoList=" + originInfoList +
                '}';
    }
}
