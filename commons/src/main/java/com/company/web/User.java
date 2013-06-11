package com.company.web;

import java.util.Date;

public class User {
	
	private String name;
	private Long age;
	private Date birthDate;
	private int sex;
	private Integer[] hobbies;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Integer[] getHobbies() {
		return hobbies;
	}
	public void setHobbies(Integer[] hobbies) {
		this.hobbies = hobbies;
	}
	
	
}
