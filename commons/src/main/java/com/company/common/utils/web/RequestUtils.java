package com.company.common.utils.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import com.company.common.utils.web.convertor.DateConvertor;

public final class RequestUtils {

	
	public static <T> T toBean(Class<T> clazz,HttpServletRequest request) throws Exception{
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}
	public static <T> T toBean(Class<T> clazz,HttpServletRequest request,Map<Class<?>,Converter> convertors) throws Exception{
		T obj = instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}
	public static <T> T toBean(Class<T> clazz,HttpServletRequest request,String datePattern) throws Exception{
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}
	public static <T> T toBean(Class<T> clazz,Map<String, Object> params) throws Exception{
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, params);
		return obj;
	}
	public static <T> T toBean(Class<T> clazz,Map<String, Object> params,Map<Class<?>,Converter> convertors) throws Exception{
		T obj = instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, params);
		return obj;
	}
	public static <T> T toBean(Class<T> clazz,Map<String, Object> params,String datePattern) throws Exception{
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, params);
		return obj;
	}
	/**
	 * 自定义默认类型转换器
	 */
	private static void initDefaultConvertor(){ 
		ConvertUtils.register(new DateConvertor(), Date.class);
		ConvertUtils.register(new LongConverter(null), Long.class);  
	    	ConvertUtils.register(new ShortConverter(null), Short.class);  
	    	ConvertUtils.register(new IntegerConverter(null), Integer.class);  
	    	ConvertUtils.register(new DoubleConverter(null), Double.class);  
	    	ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class); 
	}
	/**
	 * 初始化转换器
	 * @param convertors
	 */
	private static void initConvertor(Map<Class<?>,Converter> convertors){
		initDefaultConvertor();
		for (Map.Entry<Class<?>, Converter> convertor : convertors.entrySet()) {
			ConvertUtils.register(convertor.getValue(), convertor.getKey());
		}
		
	}
	public static <T> T instantiate(Class<T> clazz) throws Exception {
		if (clazz.isInterface()) {
			throw new Exception("Specified class is an interface");
		}
		try {
			return clazz.newInstance();
		}
		catch (Exception ex) {
			throw new Exception("Is it an abstract class?", ex);
		}
	}
}
