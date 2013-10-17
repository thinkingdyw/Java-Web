package com.think.core.zk.parser;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ZookeeperNamespaceHandler extends NamespaceHandlerSupport{

	public void init() {
		//注册一个bean解析器
		
		//注册zookeeper configurtion bean解析器
		registerBeanDefinitionParser("zkConfig", new ZookeeperConfigurationParser());  
	}
}
