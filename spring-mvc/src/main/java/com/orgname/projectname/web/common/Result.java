package com.orgname.projectname.web.common;


/**
 * 通用的请求结果，便于扩展
 * @author diaoyouwei
 *
 * @param <T>
 */
public class Result<T> {

	public final static int SUCCESS = 1; 
	public final static int FAIL = 0;
	
	private int statusCode = SUCCESS;
	private String msg;
	private T data;
	public String getMsg() {
		return msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public void fail(String errorMessage){
        this.msg = errorMessage;
        this.statusCode = FAIL;
    }
    public void fail(){
        this.statusCode = FAIL;
    }
	
    public void success(){
        this.statusCode = SUCCESS;
    }
    public void success(String message){
        this.msg = message;
        this.statusCode = SUCCESS;
    }
}
