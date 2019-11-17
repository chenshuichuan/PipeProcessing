package com.ruoyi.project.process.scanCode.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;

import java.util.Date;

/**
 * 扫码记录表 process_scan_code
 *
 * @author ricardo
 * @date 2019-11-16
 */
@Entity
@Table(name = "process_scan_code")
public class ScanCode extends BaseEntity {
  private static final long serialVersionUID = 1L;

  /**  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;
  /**
   * 扫码内容
   */
  @Column(name = "content")
  private String content;
  /**
   * 扫码工人id
   */
  @Column(name = "worker_id")
  private Integer workerId;
  /**
   * 管子id
   */
  @Column(name = "pipe_id")
  private Integer pipeId;
  /**
   * 工序
   */
  @Column(name = "state")
  private Integer state;
  /**
   * 更新时间
   */
  @Column(name = "update_time")
  private Date updateTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getWorkerId() {
    return workerId;
  }

  public void setWorkerId(Integer workerId) {
    this.workerId = workerId;
  }

  public Integer getPipeId() {
    return pipeId;
  }

  public void setPipeId(Integer pipeId) {
    this.pipeId = pipeId;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  @Override
  public Date getUpdateTime() {
    return updateTime;
  }
  @Override
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("workerId", getWorkerId())
            .append("pipeId", getPipeId())
            .append("state", getState())
            .append("updateTime", getUpdateTime())
            .toString();
  }
}
