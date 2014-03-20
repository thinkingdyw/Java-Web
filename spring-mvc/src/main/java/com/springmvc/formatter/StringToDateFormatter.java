package com.springmvc.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 * 利用Spring的字段格式化系统实现类型转换 ：String to Date，
 * 说明：
 * 	  使用formatter，存在一个容易忽视的问题：如果在转换时发生异常，会将异常信息放到BindingResult对象中。
 *   如果没有在controller的方法中明确生命该类型的参数，则回调到默认的错误页面：
 * @author diaoyouwei
 *
 */
public class StringToDateFormatter implements Formatter<Date>{

	private final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	private String pattern = DEFAULT_DATE_FORMAT;
	
	@Override
	public String print(Date object, Locale locale) {
		if(null != object){
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.format(object);
		}
		return null;
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		if(null != text){
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			return dateFormat.parse(text);
		}
		return null;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
