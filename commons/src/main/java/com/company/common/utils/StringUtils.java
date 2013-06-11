package com.company.common.utils;

public final class StringUtils {

	public static boolean isNull(String value){
		return value==null;
	}
	/**
     * <p>Checks if a String is empty ("") or null.</p>
     * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @return <code>true</code> if the String is empty or null
     */
	public static boolean isEmpty(String value){
		return isNull(value) || value.length() == 0;
	}
	/**
     * <p>Checks if a String is empty ("") or null.</p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * @return <code>true</code> if the String is empty or null
     */
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
