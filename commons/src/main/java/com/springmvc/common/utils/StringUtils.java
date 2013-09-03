package com.springmvc.common.utils;

public final class StringUtils {

	public static boolean isNull(String value){
		return value==null;
	}
	
	public static boolean isEmpty(String value){
		return isNull(value) || value.length() == 0;
	}
	
	public static boolean isNotEmpty(String str) {
        	return !isEmpty(str);
    	}
	public static boolean isNotBlank(String str) {
        	return !StringUtils.isBlank(str);
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
}
