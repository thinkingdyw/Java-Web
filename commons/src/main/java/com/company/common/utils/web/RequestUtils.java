package com.company.common.utils.web;

import java.lang.reflect.Constructor;
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

	private final String AJAX_REQUEST = "XMLHttpRequest";
	private final String AJAX_REQUEST_HEAD = "x-requested-with";
	
	/**
	 * 是否Ajax异步请求
	 **/
	private boolean isAjaxRequest(HttpServletRequest request){
		final String ajaxRequest = request.getHeader(AJAX_REQUEST_HEAD);
		if(null != ajaxRequest
			&& AJAX_REQUEST.equals(ajaxRequest)){
			
			return true;
			
		}
		return false;
	}
	/**
	 * Ajax 方式返回相应信息
	 **/
	public void ajaxWrite(HttpServletResponse response,String url){
		response.setContentType("text/html;charset=utf-8");
		
		try{
			response.sendRedirect(url);	
		}catch(IOException e){
			
		}
		
	}
	public static <T> T toBean(Class<T> clazz, HttpServletRequest request)
			throws Exception {
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, HttpServletRequest request,
			Map<Class<?>, Converter> convertors) throws Exception {
		T obj = instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, HttpServletRequest request,
			String datePattern) throws Exception {
		T obj = instantiate(clazz);
		initDefaultConvertor();
		ConvertUtils.register(new DateConvertor(datePattern), Date.class);
		BeanUtils.populate(obj, request.getParameterMap());
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params)
			throws Exception {
		T obj = instantiate(clazz);
		initDefaultConvertor();
		BeanUtils.populate(obj, params);
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params,
			Map<Class<?>, Converter> convertors) throws Exception {
		T obj = instantiate(clazz);
		initConvertor(convertors);
		BeanUtils.populate(obj, params);
		return obj;
	}

	public static <T> T toBean(Class<T> clazz, Map<String, Object> params,
			String datePattern) throws Exception {
		T obj = instantiate(clazz);
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

	public static <T> T instantiate(Class<T> clazz) throws Exception {
		if (clazz.isInterface()) {
			throw new Exception("Specified class is an interface");
		}
		try {
			return clazz.newInstance();
		} catch (Exception ex) {
			throw new Exception("Is it an abstract class?", ex);
		}
	}

	public static <T> T instantiateClass(Class<T> clazz) throws Exception {
		if (clazz.isInterface()) {
			throw new Exception("Specified class is an interface");
		}
		return instantiateClass(clazz.getDeclaredConstructor());
	}

	public static <T> T instantiateClass(Constructor<T> ctor, Object... args)
			throws Exception {
		try {
			ctor.setAccessible(true);
			return ctor.newInstance(args);
		} catch (Exception ex) {
			throw new Exception("Is it an abstract class?");
		}
	}
}
