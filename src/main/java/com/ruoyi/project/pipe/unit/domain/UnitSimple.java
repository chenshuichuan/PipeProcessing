package com.ruoyi.project.pipe.unit.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 加工单元表 pipe_unit
 *
 * @author ricardo
 * @date 2019-03-04
 */
@Entity
@Table(name = "pipe_unit")
public class UnitSimple {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 单元名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 号船代号
     */
    @Column(name = "ship_code")
    private String shipCode;
    /**
     * 批次名称
     */
    @Column(name = "batch_name")
    private String batchName;
    /**
     * 计划id-process_id
     */
    @Column(name = "plan_id")
    private Integer planId;
    /**
     * 是否完工
     */
    @Column(name = "is_finished")
    private Integer isFinished;

    public UnitSimple() {
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

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }

    public Integer getIsFinished() {
        return isFinished;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("name", getName())
                .append("shipCode", getShipCode())
                .append("batchName", getBatchName())
                .append("planId", getPlanId())
                .append("isFinished", getIsFinished())
                .toString();
    }
}
