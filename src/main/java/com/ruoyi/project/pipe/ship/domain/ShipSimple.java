package com.ruoyi.project.pipe.ship.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 号船表 pipe_ship
 * 
 * @author ricardo
 * @date 2019-03-04
 */
@Entity
@Table(name="pipe_ship")
public class ShipSimple extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** id */
	@Id
    @Column(name = "id")
	private Integer id;
	/** 船名代码 */
    @Column(name = "ship_code")
	private String shipCode;
	/** 船名 */
    @Column(name = "ship_name")
	private String shipName;

	/** 是否完成 */
    @Column(name = "is_finished")
	private Integer isFinished;

	public ShipSimple() {
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setShipCode(String shipCode) 
	{
		this.shipCode = shipCode;
	}

	public String getShipCode() 
	{
		return shipCode;
	}
	public void setShipName(String shipName) 
	{
		this.shipName = shipName;
	}

	public String getShipName() 
	{
		return shipName;
	}

	public void setIsFinished(Integer isFinished) 
	{
		this.isFinished = isFinished;
	}

	public Integer getIsFinished() 
	{
		return isFinished;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shipCode", getShipCode())
            .append("shipName", getShipName())

            .append("updateTime", getUpdateTime())
            .append("isFinished", getIsFinished())
            .toString();
    }
}
