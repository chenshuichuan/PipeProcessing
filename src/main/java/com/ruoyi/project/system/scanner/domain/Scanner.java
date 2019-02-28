package com.ruoyi.project.system.scanner.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 扫码枪对应,记录当前 扫码枪，扫码枪绑定的工位，
扫码枪绑定工人，绑定工人的日期
当天是否表 sys_scanner
 * 
 * @author yc
 * @date 2019-02-28
 */
public class Scanner extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 扫码枪编号 */
	private String scannerCode;
	/** 工人表_id */
	private Integer workerId;
	/** 工位id */
	private Integer workplaceId;
	/** 是否锁定 */
	private Integer isLock;
	/** 绑定工人时间 */
	private Date workerBindingTime;
	/** 备注 */
	private String description;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setScannerCode(String scannerCode) 
	{
		this.scannerCode = scannerCode;
	}

	public String getScannerCode() 
	{
		return scannerCode;
	}
	public void setWorkerId(Integer workerId) 
	{
		this.workerId = workerId;
	}

	public Integer getWorkerId() 
	{
		return workerId;
	}
	public void setWorkplaceId(Integer workplaceId) 
	{
		this.workplaceId = workplaceId;
	}

	public Integer getWorkplaceId() 
	{
		return workplaceId;
	}
	public void setIsLock(Integer isLock) 
	{
		this.isLock = isLock;
	}

	public Integer getIsLock() 
	{
		return isLock;
	}
	public void setWorkerBindingTime(Date workerBindingTime) 
	{
		this.workerBindingTime = workerBindingTime;
	}

	public Date getWorkerBindingTime() 
	{
		return workerBindingTime;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}

	public String getDescription() 
	{
		return description;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scannerCode", getScannerCode())
            .append("workerId", getWorkerId())
            .append("workplaceId", getWorkplaceId())
            .append("isLock", getIsLock())
            .append("workerBindingTime", getWorkerBindingTime())
            .append("description", getDescription())
            .toString();
    }
}
