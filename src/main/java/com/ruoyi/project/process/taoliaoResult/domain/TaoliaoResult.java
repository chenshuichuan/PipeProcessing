package com.ruoyi.project.process.taoliaoResult.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;


/**
 * 管材套料结果表 process_taoliao_result
 *
 * @author ricardo
 * @date 2019-04-19
 */
@Entity
@Table(name = "process_taoliao_result")
public class TaoliaoResult extends BaseEntity {
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
     * 管材
     */
    @Column(name = "pipe_id")
    private Integer pipeId;
    /**  */
    @Column(name = "batch_id")
    private Integer batchId;
    /**
     * 批次
     */
    @Column(name = "batch_name")
    private String batchName;
    /**
     * 集配管号
     */
    @Column(name = "collecte_code")
    private String collecteCode;
    /**  */
    @Column(name = "surface_treat")
    private String surfaceTreat;
    /**
     * 管形态
     */
    @Column(name = "pipe_shape")
    private String pipeShape;
    /**
     * 不校装
     */
    @Column(name = "no_install")
    private String noInstall;
    /**
     * 下料长
     */
    @Column(name = "cut_length")
    private Integer cutLength;
    /**
     * 管材
     */
    @Column(name = "pipe_material")
    private String pipeMaterial;
    /**
     * 原材id
     */
    @Column(name = "origin_id")
    private Integer originId;
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

    public TaoliaoResult(Integer taoliaoId, Integer pipeId, Integer batchId, String batchName,
                         String collecteCode, String surfaceTreat, String pipeShape, String noInstall,
                         Integer cutLength, String pipeMaterial, Integer originId,
                         Integer originLength, Integer originIndex, String algorithm) {
        this.taoliaoId = taoliaoId;
        this.pipeId = pipeId;
        this.batchId = batchId;
        this.batchName = batchName;
        this.collecteCode = collecteCode;
        this.surfaceTreat = surfaceTreat;
        this.pipeShape = pipeShape;
        this.noInstall = noInstall;
        this.cutLength = cutLength;
        this.pipeMaterial = pipeMaterial;
        this.originId = originId;
        this.originLength = originLength;
        this.originIndex = originIndex;
        this.algorithm = algorithm;
    }
    public TaoliaoResult() {

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

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setCollecteCode(String collecteCode) {
        this.collecteCode = collecteCode;
    }

    public String getCollecteCode() {
        return collecteCode;
    }

    public void setSurfaceTreat(String surfaceTreat) {
        this.surfaceTreat = surfaceTreat;
    }

    public String getSurfaceTreat() {
        return surfaceTreat;
    }

    public void setPipeShape(String pipeShape) {
        this.pipeShape = pipeShape;
    }

    public String getPipeShape() {
        return pipeShape;
    }

    public void setNoInstall(String noInstall) {
        this.noInstall = noInstall;
    }

    public String getNoInstall() {
        return noInstall;
    }

    public void setCutLength(Integer cutLength) {
        this.cutLength = cutLength;
    }

    public Integer getCutLength() {
        return cutLength;
    }

    public void setPipeMaterial(String pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
    }

    public String getPipeMaterial() {
        return pipeMaterial;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }

    public Integer getOriginId() {
        return originId;
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

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("taoliaoId", getTaoliaoId())
                .append("pipeId", getPipeId())
                .append("batchId", getBatchId())
                .append("batchName", getBatchName())
                .append("collecteCode", getCollecteCode())
                .append("surfaceTreat", getSurfaceTreat())
                .append("pipeShape", getPipeShape())
                .append("noInstall", getNoInstall())
                .append("cutLength", getCutLength())
                .append("pipeMaterial", getPipeMaterial())
                .append("originId", getOriginId())
                .append("originLength", getOriginLength())
                .append("originIndex", getOriginIndex())
                .append("algorithm", getAlgorithm())
                .toString();
    }
}
