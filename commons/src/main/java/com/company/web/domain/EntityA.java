package com.company.web.domain;

import java.util.Date;
/**
 * 
 *@author thinkingdyw
 *----------------------------------
 * 2013-6-12 
 * email:thinkingdyw@gmail.com
 */
public class EntityA {
	
	private Long id;
	private String name;
	private Date createTime;
	private Integer[] hobbies;
	private EntityB b = new EntityB();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(Integer[] hobbies) {
		this.hobbies = hobbies;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public EntityB getB() {
		return b;
	}
	public void setB(EntityB b) {
		this.b = b;
	}
	
}
