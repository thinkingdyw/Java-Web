package com.orgname.projectname.core.dictionary;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

/**
 * 枚举类型数据字典解析器
 * 
 * 配置文件格式：properties文件
 * 
 * 例如：
 * 		taskStatus=com.app.enums.TaskStatus
 * 
 * @author diaoyouwei
 *
 */
public final class EnumDictionaryResolver implements DictionaryResolver{
	private final String DEFAULT_DIC_FILE = "dictionary.properties";
	private String file;
	/**
	 * 字典接口
	 * @author diaoyouwei
	 *
	 */
	public interface DicData {
		public int value();
		public String getDesc();
	}
	
	@Override
	public Map<String, Map<String, Object>>  resolve() throws DictionaryResolveException {
		Map<String, Map<String, Object>> dic = new ConcurrentHashMap<String, Map<String, Object>>();
		try {
			Properties prop = read(getFile());
			Set<Object> keySet = prop.keySet();
			for (Object e : keySet) {
				final String indexName = (String) e;
				final String classFullName = prop.getProperty(indexName);
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
			ifImplemets(clazz,DicData.class);
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
	private boolean ifImplemets(Class<?> clazz,Class<?> targetInterface) {
		boolean implemented = false;
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> inter : interfaces) {
			if(targetInterface == inter){
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
	public Properties read(String dicFile) throws Exception {
		Properties prop = new Properties();
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(classLoader == null){
			classLoader = this.getClass().getClassLoader();
		}
		InputStream in = classLoader.getResourceAsStream(dicFile);
		if(in == null){
			if(!DEFAULT_DIC_FILE.equals(file)){
				throw new FileNotFoundException("classpath:字典配置文件["+dicFile+"]未找到!");
			}
		}
		try {
			prop.load(in);
		} catch (Exception e) {
			throw e;
		}finally{
			if(in != null){
				in.close();
			}
		}
		return prop;
	}
	public String getFile() {
		return null == file ? DEFAULT_DIC_FILE:file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
}
