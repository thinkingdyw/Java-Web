package com.springmvc.common.utils.validation.rule;

public class RuleUtils {

	public static boolean isNull(Object value){
		return value == null;
	}
	
	public static boolean isEmpty(String value){
		return isNull(value) || value.length() == 0;
	}

	public static boolean isBlank(String value) {
		if (isEmpty(value)) {
			return true;
		}
		final int strLen = value.length();
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	public static String valueOf(Object value) {
		if(isNull(value)){
			return null;
		}else{
			return String.valueOf(value);
		}
	}
}
