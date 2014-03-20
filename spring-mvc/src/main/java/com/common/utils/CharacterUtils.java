package com.common.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 字符工具类
 * 
 * @author diaoyouwei
 * 
 */
public class CharacterUtils {

	/**
	 * 是否包含全角字符，不包括汉字
	 * @param str
	 * @return
	 */
	public static boolean containSBC(String str) {
		if (str == null) {
			return false;
		}
		final int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (isSBC(c)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 查找指定字符串中的全角字符，不包括汉字
	 * 
	 * @param str
	 * @return
	 */
	public static Set<Character> getSBC(String str) {
		Set<Character> chars = new HashSet<Character>();
		if (str == null) {
			return chars;
		}
		final int len = str.length();
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (isSBC(c))
				chars.add(c);
		}
		return chars;
	}

	/**
	 * 判断字符是否是全角字符，不包括汉字
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isSBC(char c) {
		if (('\uff61' <= c && c <= '\uff9f')
				|| ('\u0000' <= c && c <= '\u00ff')
				|| ('\uffe8' <= c && c <= '\uffee')
				|| ('\u4E00' <= c && c <= '\u9FA5')) {
			return false;
		}
		return true;
	}
}
