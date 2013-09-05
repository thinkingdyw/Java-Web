package com.dto;

import java.util.Date;

public class EntityADTO {

	private Long id;
	private String email;
	private Integer city;
	private Long phone;
	private String psw;
	private String psw2;
	private Date date;
	private EntityB entityB;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPsw() {
		return psw;
	}
	public void setPsw(String psw) {
		this.psw = psw;
	}
	public String getPsw2() {
		return psw2;
	}
	public void setPsw2(String psw2) {
		this.psw2 = psw2;
	}
	public EntityB getEntityB() {
		return entityB;
	}
	public void setEntityB(EntityB entityB) {
		this.entityB = entityB;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

