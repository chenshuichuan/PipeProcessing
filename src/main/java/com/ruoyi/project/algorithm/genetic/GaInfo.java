package com.ruoyi.project.algorithm.genetic;

import com.ruoyi.project.process.taoliaoResult.domain.TaoliaoResult;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *@ClassName: GaInfo
 *@Description: TODO
 *@Author: Ricardo
 *@Date: 2019/4/21 17:12
 **/
public class GaInfo {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer taoliaoId;
    /**
     * 管材
     */
    private Integer pipeId;

    /**
     * 下料长
     */
    private Integer cutLength;

    /**
     * 原材id
     */
    private Integer originId;
    /**
     * 原材料长度
     */
    private Integer originLength;
    /**
     * 原材料编号
     */
    private Integer originIndex;

    public GaInfo(TaoliaoResult taoliaoResult) {
        this.id = taoliaoResult.getId();
        this.taoliaoId = taoliaoResult.getTaoliaoId();
        this.pipeId = taoliaoResult.getPipeId();
        this.cutLength = taoliaoResult.getCutLength();
        this.originId = taoliaoResult.getOriginId();
        this.originIndex = taoliaoResult.getOriginIndex();
        this.originLength = taoliaoResult.getOriginLength();

    }

    public void setTaoliaoOrigin(Integer originId, Integer originIndex, Integer originLength ) {
        this.originId = originId;
        this.originIndex = originIndex;
        this.originLength = originLength;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTaoliaoId(Integer taoliaoId) {
        this.taoliaoId = taoliaoId;
    }

    public Integer getTaoliaoId() {
        return taoliaoId;
    }

    public void setPipeId(Integer pipeId) {
        this.pipeId = pipeId;
    }

    public Integer getPipeId() {
        return pipeId;
    }


    public void setCutLength(Integer cutLength) {
        this.cutLength = cutLength;
    }

    public Integer getCutLength() {
        return cutLength;
    }

    public Integer getOriginLength() {
        return originLength;
    }

    public void setOriginLength(Integer originLength) {
        this.originLength = originLength;
    }

    public Integer getOriginIndex() {
        return originIndex;
    }

    public void setOriginIndex(Integer originIndex) {
        this.originIndex = originIndex;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getOriginId() {
        return originId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taoliaoId", getTaoliaoId())
                .append("pipeId", getPipeId())
                .append("cutLength", getCutLength())
                .append("originId", getOriginId())
                .append("originIndex", getOriginIndex())
                .append("originLength", getOriginLength())
                .toString();
    }
}
