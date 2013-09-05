package com.web.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 * 实现全局：字符串到日期类型的转换
 * @author diaoyouwei
 *
 */
public class StringToDateConverter implements Converter<String, Date>{
	private Logger logger = Logger.getLogger(StringToDateConverter.class);
	/**
	 * 默认的日期格式
	 */
	private final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private String pattern = DEFAULT_DATE_FORMAT;
	@Override
	public Date convert(String source) {
		//TODO 待优化，性能优化
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		if(StringUtils.isNotBlank(source)){
			try {
				return dateFormat.parse(source);
			} catch (ParseException e) {
				logger.error("指定的日期格式错误:"+source);
				return null;
			}
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
