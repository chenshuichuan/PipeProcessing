package com.ruoyi.project.pipe.pipPipe.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;


/**
 * 中间数据库Pipe表 pipe_pip_pipe
 *
 * @author ricardo
 * @date 2019-03-06
 */
@Entity
@Table(name = "pipe_pip_pipe")
public class PipPipe extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    /**  */
    @Column(name = "area_code")
    private String areaCode;
    /**  */
    @Column(name = "segment_code")
    private String segmentCode;
    /**  */
    @Column(name = "tray_code")
    private String trayCode;
    /**  */
    @Column(name = "assembly_code")
    private String assemblyCode;
    /**  */
    @Column(name = "set_code")
    private String setCode;

    public PipPipe() {

    }

    public PipPipe(Integer id, String areaCode, String segmentCode, String trayCode, String assemblyCode, String setCode) {
        this.id = id;
        this.areaCode = areaCode;
        this.segmentCode = segmentCode;
        this.trayCode = trayCode;
        this.assemblyCode = assemblyCode;
        this.setCode = setCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }

    public String getSegmentCode() {
        return segmentCode;
    }

    public void setTrayCode(String trayCode) {
        this.trayCode = trayCode;
    }

    public String getTrayCode() {
        return trayCode;
    }

    public void setAssemblyCode(String assemblyCode) {
        this.assemblyCode = assemblyCode;
    }

    public String getAssemblyCode() {
        return assemblyCode;
    }

    public void setSetCode(String setCode) {
        this.setCode = setCode;
    }

    public String getSetCode() {
        return setCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("areaCode", getAreaCode())
                .append("segmentCode", getSegmentCode())
                .append("trayCode", getTrayCode())
                .append("assemblyCode", getAssemblyCode())
                .append("setCode", getSetCode())
                .toString();
    }
}
