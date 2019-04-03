package com.ruoyi.project.pipe.workPipe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Date;

/**
 * 管件-表 pipe_work_pipe
 *
 * @author ricardo
 * @date 2019-03-07
 */
@Entity
@Table(name = "pipe_work_pipe")
public class WorkPipe extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 装配管号ID
     */
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 号船id
     */
    @Column(name = "shape_ship_id")
    private Integer shapeShipId;
    /**
     * 装配管id
     */
    @Column(name = "assembly_pipe_id")
    private Integer assemblyPipeId;
    /**
     * 管子通径
     */
    @Column(name = "pipe_diameter")
    private double pipeDiameter;
    /**
     * 管子外径
     */
    @Column(name = "pipe_out_diameter")
    private double pipeOutDiameter;
    /**
     * 管子壁厚
     */
    @Column(name = "pipe_thickness")
    private double pipeThickness;
    /**
     * 管子材质
     */
    @Column(name = "pipe_material")
    private String pipeMaterial;
    /**
     * 管材级别
     */
    @Column(name = "pipe_material_level")
    private String pipeMaterialLevel;
    /**
     * 管段形态
     */
    @Column(name = "pipe_shape")
    private String pipeShape;
    /**
     * 表处简称
     */
    @Column(name = "surface_treat")
    private String surfaceTreat;

    /**
     * 下料长
     */
    @Column(name = "cut_length")
    private double cutLength;
    /**
     * 外场装配标记
     */
    @Column(name = "outfield")
    private String outfield;
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    public WorkPipe() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setShapeShipId(Integer shapeShipId) {
        this.shapeShipId = shapeShipId;
    }

    public Integer getShapeShipId() {
        return shapeShipId;
    }

    public void setAssemblyPipeId(Integer assemblyPipeId) {
        this.assemblyPipeId = assemblyPipeId;
    }

    public Integer getAssemblyPipeId() {
        return assemblyPipeId;
    }

    public void setPipeDiameter(double pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
    }

    public double getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeOutDiameter(double pipeOutDiameter) {
        this.pipeOutDiameter = pipeOutDiameter;
    }

    public double getPipeOutDiameter() {
        return pipeOutDiameter;
    }

    public void setPipeThickness(Double pipeThickness) {
        this.pipeThickness = pipeThickness;
    }

    public Double getPipeThickness() {
        return pipeThickness;
    }

    public void setPipeMaterial(String pipeMaterial) {
        this.pipeMaterial = pipeMaterial;
    }

    public String getPipeMaterial() {
        return pipeMaterial;
    }

    public void setPipeMaterialLevel(String pipeMaterialLevel) {
        this.pipeMaterialLevel = pipeMaterialLevel;
    }

    public String getPipeMaterialLevel() {
        return pipeMaterialLevel;
    }

    public void setPipeShape(String pipeShape) {
        this.pipeShape = pipeShape;
    }

    public String getPipeShape() {
        return pipeShape;
    }

    public void setSurfaceTreat(String surfaceTreat) {
        this.surfaceTreat = surfaceTreat;
    }

    public String getSurfaceTreat() {
        return surfaceTreat;
    }

    public void setCutLength(double cutLength) {
        this.cutLength = cutLength;
    }

    public double getCutLength() {
        return cutLength;
    }

    public void setOutfield(String outfield) {
        this.outfield = outfield;
    }

    public String getOutfield() {
        return outfield;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("shapeShipId", getShapeShipId())
                .append("assemblyPipeId", getAssemblyPipeId())
                .append("pipeDiameter", getPipeDiameter())
                .append("pipeOutDiameter", getPipeOutDiameter())
                .append("pipeThickness", getPipeThickness())
                .append("pipeMaterial", getPipeMaterial())
                .append("pipeMaterialLevel", getPipeMaterialLevel())
                .append("pipeShape", getPipeShape())
                .append("surfaceTreat", getSurfaceTreat())
                .append("cutLength", getCutLength())
                .append("outfield", getOutfield())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
