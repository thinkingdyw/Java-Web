package com.commons;

/**
 * 通用的请求结果，便于扩展
 * @author diaoyouwei
 *
 * @param <T>
 */
public class Result<T> {

	public final static int SUCCESS = 0; 
	public final static int FAIL = 1;
	private int statusCode;
	private String msg;
	private T data;
	/**
	 * 自定义扩展属性
	 */
	private String attr1;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	
}
