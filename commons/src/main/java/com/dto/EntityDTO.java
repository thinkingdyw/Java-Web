package com.dto;

import java.util.Date;

public class EntityDTO {

	public Long attribute1;
	public String attribute2;
	public Integer attribute3;
	public Integer[] attribute4;
	public Date attribute5;
	public Enum<?>	attribute6;
	public Long getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(Long attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public Integer getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}
	public Integer[] getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(Integer[] attribute4) {
		this.attribute4 = attribute4;
	}
	public Date getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(Date attribute5) {
		this.attribute5 = attribute5;
	}
	public Enum<?> getAttribute6() {
		return attribute6;
	}
	public void setAttribute6(Enum<?> attribute6) {
		this.attribute6 = attribute6;
	}
}
