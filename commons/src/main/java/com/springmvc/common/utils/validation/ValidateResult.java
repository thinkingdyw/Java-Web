package com.springmvc.common.utils.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidateResult {

	private boolean error = false;
	private int code;//错误代码
	/**
	 * 错误消息map类型;
	 * key:字段名 example:id,name...
	 * value:错误信息
	 */
	private Map<String, String> errorMsg = new HashMap<String, String>();
	public boolean hasError() {
		return error;
	}
	public int getCode() {
		return code;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
