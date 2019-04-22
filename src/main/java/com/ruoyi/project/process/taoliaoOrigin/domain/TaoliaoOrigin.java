package com.ruoyi.project.process.taoliaoOrigin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;


/**
 * 套料原材料表 process_taoliao_origin
 *
 * @author ricardo
 * @date 2019-04-19
 */
@Entity
@Table(name = "process_taoliao_origin")
public class TaoliaoOrigin  {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**
     * 对应的process_taoliao的id
     */
    @Column(name = "taoliao_id")
    private Integer taoliaoId;
    /**
     * 原材料
     */
    @Column(name = "material")
    private String material;
    /**
     * 原材料长度
     */
    @Column(name = "origin_length")
    private Integer originLength;
    /**
     * 原材料编号
     */
    @Column(name = "origin_index")
    private Integer originIndex;
    /**
     * 套料算法
     */
    @Column(name = "algorithm")
    private String algorithm;
    /**
     * 剩余长度
     */
    @Column(name = "remain_length")
    private Integer remainLength;
    /**
     * 套料管子数
     */
    @Column(name = "pipe_number")
    private Integer pipeNumber;

    public TaoliaoOrigin(Integer taoliaoId, String material, Integer originLength, Integer originIndex,
                         String algorithm, Integer remainLength, Integer pipeNumber) {
        this.taoliaoId = taoliaoId;
        this.material = material;
        this.originLength = originLength;
        this.originIndex = originIndex;
        this.algorithm = algorithm;
        this.remainLength = remainLength;
        this.pipeNumber = pipeNumber;
    }
    public TaoliaoOrigin() {

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

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setOriginLength(Integer originLength) {
        this.originLength = originLength;
    }

    public Integer getOriginLength() {
        return originLength;
    }

    public void setOriginIndex(Integer originIndex) {
        this.originIndex = originIndex;
    }

    public Integer getOriginIndex() {
        return originIndex;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setRemainLength(Integer remainLength) {
        this.remainLength = remainLength;
    }

    public Integer getRemainLength() {
        return remainLength;
    }

    public void setPipeNumber(Integer pipeNumber) {
        this.pipeNumber = pipeNumber;
    }

    public Integer getPipeNumber() {
        return pipeNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taoliaoId", getTaoliaoId())
                .append("material", getMaterial())
                .append("originLength", getOriginLength())
                .append("originIndex", getOriginIndex())
                .append("algorithm", getAlgorithm())
                .append("remainLength", getRemainLength())
                .append("pipeNumber", getPipeNumber())
                .toString();
    }
}
