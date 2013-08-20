package com.company.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 占位符属性配置
 **/
public final class PropertiesPlaceholderConfigureFactoryBean {

	private String configFile;//配置文件，位于classpath下
	private Set<String> configFiles;//配置文件，位于classpath下
	private Map<String, String> config = new HashMap<String, String>();//解析后的配置信息
	private PlaceholderResolver placeholderResolver;
	private Set<Properties> configs = new HashSet<Properties>();
	
	public void parse() throws Exception{
		loadConfigure(configFile);
		loadConfigure(configFiles);
		this.resolve();
	}
	/**
	 * 解析配置文件
	 **/
	private void resolve() {
		if(null == placeholderResolver){
			//如果没有指定配置文件解析器，则使用默认的解析器
			placeholderResolver = new PropertiesPlaceholderResolver();
		}
		if(this.configs.size() > 0){
			for (Properties properties : configs) {
				Map<String, String> map = this.placeholderResolver.resolve(properties);
				this.config.putAll(map);
			}
		}
	}
	/**
	 * 加载配置文件
	 **/
	private void loadConfigure(Set<String> configFiles) throws Exception {
		if(null != configFiles && configFiles.size() >0){
			for (String config : configFiles) {
				this.loadConfigure(config);
			}
		}
	}
	/**
	 * 加载配置文件
	 **/
	public void loadConfigure(String configFile) throws Exception{
		Properties properties = getProperties(configFile);
		configs.add(properties);
	}
	/**
	 * 根据配置信息获取properties文件实例
	 **/
	private Properties getProperties(String configFile) throws IOException{
		ClassLoader classLoader = getClassLoader();
		InputStream in= classLoader.getResourceAsStream(configFile);
		if(null == in){
			throw new FileNotFoundException("找不到:"+configFile+".properties!");
		}
		Properties properties = new Properties();
		properties.load(in);
		in.close();
		return properties;
	}
	/**
	 * 获得类加载器
	 **/
	private ClassLoader getClassLoader() {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if(null == classLoader){
			classLoader = PropertiesPlaceholderFactoryBean.class.getClassLoader();
		}
		return classLoader;
	}
	
	
	public String getConfigFile() {
		return configFile;
	}

	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public Set<String> getConfigFiles() {
		return configFiles;
	}

	public void setConfigFiles(Set<String> configFiles) {
		this.configFiles = configFiles;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	public PlaceholderResolver getPlaceholderResolver() {
		return placeholderResolver;
	}

	public void setPlaceholderResolver(PlaceholderResolver placeholderResolver) {
		this.placeholderResolver = placeholderResolver;
	}
}
