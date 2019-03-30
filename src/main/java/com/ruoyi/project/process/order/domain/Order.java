package com.ruoyi.project.process.order.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.persistence.*;
import java.util.Date;


/**
 * 加工顺序表 process_order
 * 
 * @author ricardo
 * @date 2019-03-08
 */
@Entity
@Table(name="process_order")
public class Order extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 加工顺序id */
	@Id
	@GeneratedValue
    @Column(name = "id")
	private Integer id;
	/** 加工顺序名称 */
    @Column(name = "name")
	private String name;
	/** 顺序列表，以逗号隔开 */
    @Column(name = "order_list")
	private String orderList;

	/** 规则更新时间 */
	@Column(name = "update_time")
	private Date updateTime;
	/** 单元名称包含F */
	@Column(name = "unit_has_f")
	private Integer unitHasF;
	/** 管形态包含‘弯字’ */
	@Column(name = "pipe_shape_has_bend")
	private Integer pipeShapeHasBend;
	/** 需要进行表面处理 */
	@Column(name = "has_surface_treat")
	private Integer hasSurfaceTreat;

	public Order() {
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
	public void setOrderList(String orderList)
	{
		this.orderList = orderList;
	}

	public String getOrderList()
	{
		return orderList;
	}
	@Override
	public void setUpdateTime(Date updateTime)
	{
		this.updateTime = updateTime;
	}
	@Override
	public Date getUpdateTime()
	{
		return updateTime;
	}
	public void setUnitHasF(Integer unitHasF)
	{
		this.unitHasF = unitHasF;
	}

	public Integer getUnitHasF()
	{
		return unitHasF;
	}
	public void setPipeShapeHasBend(Integer pipeShapeHasBend)
	{
		this.pipeShapeHasBend = pipeShapeHasBend;
	}

	public Integer getPipeShapeHasBend()
	{
		return pipeShapeHasBend;
	}
	public void setHasSurfaceTreat(Integer hasSurfaceTreat)
	{
		this.hasSurfaceTreat = hasSurfaceTreat;
	}

	public Integer getHasSurfaceTreat()
	{
		return hasSurfaceTreat;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("id", getId())
				.append("name", getName())
				.append("orderList", getOrderList())
				.append("updateTime", getUpdateTime())
				.append("unitHasF", getUnitHasF())
				.append("pipeShapeHasBend", getPipeShapeHasBend())
				.append("hasSurfaceTreat", getHasSurfaceTreat())
				.toString();
	}
}
