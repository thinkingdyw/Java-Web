package com.web.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 * 实现全局：字符串到日期类型的转换,同时支持多种格式转换
 * @author diaoyouwei
 *
 */
public class StringToDateConverter implements Converter<String, Date>{
	private Logger log = Logger.getLogger(getClass());
	/**
	 * 默认的日期格式
	 */
	private final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private Set<String> patterns;
	@Override
	public Date convert(String source) {
		//TODO 待优化，性能优化
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		if(null != patterns && !patterns.isEmpty()){
			for (String pattern : patterns) {
				if(StringUtils.isNotBlank(source)){
					try {
						return dateFormat.parse(source);
					} catch (ParseException ex) {
					}
				}
				dateFormat.applyPattern(pattern);
			}
		}else{
			dateFormat.applyPattern(DEFAULT_DATE_FORMAT);
			try {
				return dateFormat.parse(source);
			} catch (ParseException ex) {
			}
		}
		log.info(source+":日期格式错误!不能实现转换,返回null");
		return null;
	}
	public Set<String> getPatterns() {
		return patterns;
	}
	/**
	 * 设置多种日期格式，最终选择合适的，否则返回null
	 * @param patterns
	 */
	public void setPatterns(Set<String> patterns) {
		this.patterns = patterns;
	}
}
