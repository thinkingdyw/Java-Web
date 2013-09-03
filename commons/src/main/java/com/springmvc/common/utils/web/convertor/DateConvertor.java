package com.springmvc.common.utils.web.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConvertor implements Converter {

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	private String pattern;

	public DateConvertor() {
	}

	public DateConvertor(String pattern) {
		this.pattern = pattern;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object convert(Class type, Object value) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				null == pattern ? DEFAULT_DATE_FORMAT : pattern);
		try {
			if (type == Date.class) {
				return dateFormat.parse(String.valueOf(value));
			} else {
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
