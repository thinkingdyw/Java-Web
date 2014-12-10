package com.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class DictionaryResolver {

	private String root;
	
	public interface DicData {
		public int value();

		public String getDesc();
	}
	/**
	 * 词典文档解析异常
	 * @author diaoyouwei
	 *
	 */
	class DictionaryResolveException extends Exception{

		private static final long serialVersionUID = 1L;
		
		public DictionaryResolveException(String msg,Throwable e){
			super(msg,e);
		}
		
	}
	

	public Map<String, Map<String, Object>>  resovle(String dicFile) throws DictionaryResolveException {
		
		Map<String, Map<String, Object>> dic = new ConcurrentHashMap<String, Map<String, Object>>();
		try {
			Document doc = read(dicFile);
			Element rootElement = doc.getRootElement();
			if(StringUtils.isBlank(rootElement.attributeValue("name"))){
				throw new IllegalArgumentException("词典配置文件，根name属性没有指定!");
			}
			root = rootElement.attributeValue("name");
			@SuppressWarnings("unchecked")
			List<Element> elements = rootElement.elements();
			final int size = elements.size();
			for (int i = 0;i<size; i++) {
				Element e = elements.get(i);
				final String indexName = e.attributeValue("name");
				final String classFullName = e.attributeValue("class");
				if(StringUtils.isNotBlank(classFullName)){
					Map<String, Object> vals = enumSerialize(classFullName);
					dic.put(indexName, vals);
				}else{
					throw new IllegalArgumentException("词典配置文件，"+indexName+"[class]属性没有指定!");
				}
				
			}
			return dic;
		} catch (Exception e) {
			throw new DictionaryResolveException("解析词典配置失败!",e);
		}
	}
	/**
	 * 序列化枚举
	 * @param classFullName
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> enumSerialize(String classFullName) throws Exception{
		Map<String, Object> mapping = new ConcurrentHashMap<String, Object>();
		Class<?> clazz = Class.forName(classFullName.trim());
		if(clazz.isEnum()){
			implemetsDicData(clazz);
			@SuppressWarnings("unchecked")
			Class<Enum<?>> enumClazz = (Class<Enum<?>>) Class.forName(classFullName);
			Enum<?>[] enums = enumClazz.getEnumConstants();
			for (Enum<?> enum1 : enums) {
				Class<?> claz = enum1.getDeclaringClass();
				Method value = claz.getMethod("value");
				
				mapping.put(enum1.toString(), value.invoke(enum1));
			}
		}
		return mapping;
	}
	/**
	 * 判断类是否实现了DicData接口
	 * @param clazz
	 * @return
	 */
	private boolean implemetsDicData(Class<?> clazz) {
		boolean implemented = false;
		Class<?>[] interfaces = clazz.getInterfaces();
		
		for (Class<?> inter : interfaces) {
			if(DicData.class == inter){
				implemented = true;
				break;
			}
		}
		
		if(!implemented){
			throw new IllegalArgumentException(clazz.getClass()+"未实现接口："+DicData.class);
		}
		return false;
	}

	/**
	 * 读取配置文件
	 * @return
	 * @throws Exception
	 */
	public Document read(String dicFile) throws Exception {
		SAXReader reader = new SAXReader();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		if(classLoader == null){
			classLoader = this.getClass().getClassLoader();
		}
		InputStream in = classLoader.getResourceAsStream(dicFile);
		if(in == null){
			throw new FileNotFoundException("classpath:词典配置文件["+dicFile+"]未找到!");
		}
		Document document;
		try {
			document = reader.read(in);
		} catch (Exception e) {
			throw e;
		}finally{
			if(in != null){
				in.close();
			}
		}
		return document;
	}
	/**
	 * 返回词典的根索引
	 * @return
	 */
	public String getRoot() {
		return root;
	}

}
