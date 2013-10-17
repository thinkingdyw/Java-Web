package com.zk.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.think.core.zk.config.ZookeeperConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations={"classpath:app-config.xml"}) 
public class ZkConfigTest {

	ZookeeperConfiguration zkConfig;
	
	@Test
	public void testGetZkConfig(){

		ApplicationContext ctx = new ClassPathXmlApplicationContext("app-config.xml");  
		zkConfig = ctx.getBean("zkConfig",ZookeeperConfiguration.class);  
		System.out.println(zkConfig.getHost());  
		System.out.println(zkConfig.getTimeOut());  

	}
	
	
}
