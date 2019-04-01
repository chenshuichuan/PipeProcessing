package com.ruoyi.project.process.middleStatus.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 中间数据读取程序状态表 pipe_middle_status
 *
 * @author ricardo
 * @date 2019-04-01
 */
@Entity
@Table(name = "pipe_middle_status")
public class MiddleStatus extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**  */
    /**
     * id
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    /**
     * 错误描述
     */
    @Column(name = "infor")
    private String infor;
    /**
     * 涉及表格
     */
    @Column(name = "relate_table")
    private String relateTable;
    /**
     * 涉及数据
     */
    @Column(name = "relate_data")
    private String relateData;
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 操作类型
     */
    @Column(name = "operate_type")
    private String operateType;
    /**
     * 标记已查看
     */
    @Column(name = "is_read")
    private Integer isRead;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public String getInfor() {
        return infor;
    }

    public void setRelateTable(String relateTable) {
        this.relateTable = relateTable;
    }

    public String getRelateTable() {
        return relateTable;
    }

    public void setRelateData(String relateData) {
        this.relateData = relateData;
    }

    public String getRelateData() {
        return relateData;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Override
    public Date getCreateTime() {
        return createTime;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getIsRead() {
        return isRead;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("infor", getInfor())
                .append("relateTable", getRelateTable())
                .append("relateData", getRelateData())
                .append("createTime", getCreateTime())
                .append("operateType", getOperateType())
                .append("isRead", getIsRead())
                .toString();
    }
}
