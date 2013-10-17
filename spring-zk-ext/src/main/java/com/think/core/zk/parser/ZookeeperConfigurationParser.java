package com.think.core.zk.parser;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;
import com.think.core.zk.config.ZookeeperConfiguration;

public class ZookeeperConfigurationParser extends AbstractSingleBeanDefinitionParser{

	
	@Override
	protected Class<?> getBeanClass(Element element) {
		return ZookeeperConfiguration.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		
		validateConfig(element);
		
	    String host = element.getAttribute("host");  
	    final int timeOut = Integer.valueOf(element.getAttribute("time-out"));
	    
	    builder.addPropertyValue("host", host);
	    builder.addPropertyValue("timeOut", timeOut);
	    
	}
	
	private void validateConfig(Element element){
	    String host = element.getAttribute("host");  
	    if(host == null
	    		|| "".equals(host.trim())){
	    	throw new IllegalArgumentException("host 配置为空!");
	    }
	}
}
