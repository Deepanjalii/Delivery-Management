package com.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_entity")
public class DeliveryEntity extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String ownerName;
	private String time;
	private Date date;
	private String status;

	public DeliveryEntity() {
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "DeliveryEntity [id=" + id + ", ownerName=" + ownerName + ", time=" + time + ", date=" + date
				+ ", status=" + status + "]";
	}

	public DeliveryEntity(String ownerName, String time, Date date, String status) {
		super();
		this.ownerName = ownerName;
		this.time = time;
		this.date = date;
		this.status = status;
	}

}
