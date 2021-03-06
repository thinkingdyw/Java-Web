package com.commons.utils;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortConverter;

import com.commons.convertor.DateConvertor;

public final class WebRequestUtils {

	private final static String AJAX_REQUEST = "XMLHttpRequest";
	private final static String AJAX_REQUEST_HEADER = "X-Requested-With";
	
	/**
	 * 是否Ajax异步请求
	 **/
	@SuppressWarnings("unused")
	private static boolean isAsyncRequest(HttpServletRequest request){
		final String ajaxRequest = request.getHeader(AJAX_REQUEST_HEADER);
		if(null != ajaxRequest && AJAX_REQUEST.equals(ajaxRequest)){
			return true;
		}
		return false;
	}
	/**
	 * Ajax 方式返回相应信息
	 **/
	public static void asyncWrite(HttpServletResponse response,String content) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(content);
	}
	public static <T> T toBean(Class<T> clazz, HttpServletRequest request)
			throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, HttpServletRequest request,
			Map<Class<?>, Converter> convertors) throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, HttpServletRequest request,
			String datePattern) throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initDefaultConvertor();
		ConvertUtils.register(new DateConvertor(datePattern), Date.class);
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params)
			throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, params);
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params,
			Map<Class<?>, Converter> convertors) throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, params);
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params,
			String datePattern) throws Exception {
		T obj = EntityUtils.instantiate(clazz);
		initDefaultConvertor();
		ConvertUtils.register(new DateConvertor(datePattern), Date.class);
		BeanUtils.populate(obj, params);
		return obj;
	}

	/**
	 * 自定义默认类型转换器,如果类型为引用类型，若果转换失败，则设置默认值为null
	 */
	private static void initDefaultConvertor() {
		ConvertUtils.register(new DateConvertor(), Date.class);
		ConvertUtils.register(new LongConverter(null), Long.class);
		ConvertUtils.register(new ShortConverter(null), Short.class);
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new DoubleConverter(null), Double.class);
		ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
	}

	/**
	 * 初始化转换器
	 * 
	 * @param convertors
	 */
	private static void initConvertor(Map<Class<?>, Converter> convertors) {
		initDefaultConvertor();
		if (null != convertors && convertors.size() > 0) {
			for (Map.Entry<Class<?>, Converter> convertor : convertors
					.entrySet()) {
				ConvertUtils.register(convertor.getValue(), convertor.getKey());
			}
		}
	}

}
