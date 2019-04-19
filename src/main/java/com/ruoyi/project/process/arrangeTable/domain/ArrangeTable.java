package com.ruoyi.project.process.arrangeTable.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 派工记录表 process_arrange_table
 *
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name = "process_arrange_table")
public class ArrangeTable extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 派工类型
     */
    @Column(name = "type")
    private Integer type;
    /**
     * 派工名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 对应计划id
     */
    @Column(name = "plan_id")
    private Integer planId;
    /**
     * 工段
     */
    @Column(name = "section")
    private String section;
    /**
     * 工序
     */
    @Column(name = "stage")
    private String stage;
    /**
     * 派工者
     */
    @Column(name = "arranger_id")
    private Integer arrangerId;
    /**
     * 工位 码
     */
    @Column(name = "workplace")
    private String workplace;
    /**
     * 工作者
     */
    @Column(name = "worker_id")
    private Integer workerId;
    /**
     * 是否完工
     */
    @Column(name = "is_finished")
    private Integer isFinished;
    /**
     * 完工时间
     */
    @Column(name = "finished_time")
    private Date finishedTime;
    /**
     * 派工时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 工人接单时间
     */
    @Column(name = "access_time")
    private Date accessTime;

    public ArrangeTable() {
    }

    public ArrangeTable(Integer type, String name, Integer planId, String stage, Integer arrangerId,
                        Integer isFinished, Date createTime) {
        this.type = type;
        this.name = name;
        this.planId = planId;

        this.stage = stage;
        this.arrangerId = arrangerId;
        this.workerId = workerId;
        this.isFinished = isFinished;
        this.createTime = createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public void setArrangerId(Integer arrangerId) {
        this.arrangerId = arrangerId;
    }

    public Integer getArrangerId() {
        return arrangerId;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    public void setFinishedTime(Date finishedTime) {
        this.finishedTime = finishedTime;
    }

    public Date getFinishedTime() {
        return finishedTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("type", getType())
                .append("name", getName())
                .append("planId", getPlanId())
                .append("section", getSection())
                .append("stage", getStage())
                .append("arrangerId", getArrangerId())
                .append("workplace", getWorkplace())
                .append("workerId", getWorkerId())
                .append("isFinished", getIsFinished())
                .append("finishedTime", getFinishedTime())
                .append("createTime", getCreateTime())
                .append("accessTime", getAccessTime())
                .toString();
    }
}
