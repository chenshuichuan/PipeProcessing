package com.ruoyi.project.pipe.batch.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

/**
 * 加工批次表 pipe_batch
 *
 * @author ricardo
 * @date 2019-03-04
 */
@Entity
@Table(name = "pipe_batch")
public class BatchSimple {
    private static final long serialVersionUID = 1L;

    /**
     * 批次编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 批次名称
     */
    @Column(name = "name")
    private String name;
    /**
     * 船名代码
     */
    @Column(name = "ship_code")
    private String shipCode;
    /**
     * 包含单元数
     */
    @Column(name = "unit_number")
    private Integer unitNumber;
    /**
     * 是否完工
     */
    @Column(name = "is_finished")
    private Integer isFinished;

    public BatchSimple() {
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

    public void setUnitNumber(Integer unitNumber) {
        this.unitNumber = unitNumber;
    }

    public Integer getUnitNumber() {
        return unitNumber;
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
                .append("unitNumber", getUnitNumber())
                .append("isFinished", getIsFinished())
                .toString();
    }
}
