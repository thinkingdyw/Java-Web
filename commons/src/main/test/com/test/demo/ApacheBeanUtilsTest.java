package com.test.demo;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

import com.dto.EntityA;
import com.dto.EntityADTO;
import com.dto.EntityB;

public class ApacheBeanUtilsTest {

	/**
	 * 对象之间的非结构化属性拷贝,
	 * ---------------------
	 * 测试点：存在不同的属性是否报错
	 */
	@Test
	public void copyProperties_BetweenObject_unstructured(){
		EntityA beanA = new EntityA();
		EntityADTO beanADto = new EntityADTO();
		
		beanADto.setCity(1);
		beanADto.setEmail("diaoyouwei@jd.com");
		beanADto.setPhone(1213131231l);
		beanADto.setPsw("sdafas21421dsfa");
		beanADto.setId(1323123131231231l);
		
		try {
			BeanUtils.copyProperties(beanA, beanADto);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(beanA.getEmail(),"diaoyouwei@jd.com");
	}
	/**
	 * 对象之间的结构化属性拷贝,
	 * ---------------------
	 * 测试点：存在不同的属性是否报错
	 * 结论:如果属性名相同，但是属性类型不同，不可复制
	 */
	@Test
	public void copyProperties_BetweenObject_structured(){
		EntityA beanA = new EntityA();
		EntityADTO beanADto = new EntityADTO();
		beanADto.setCity(1);
		beanADto.setEmail("diaoyouwei@jd.com");
		beanADto.setPhone(1213131231l);
		beanADto.setPsw("sdafas21421dsfa");
		beanADto.setId(1323123131231231l);
		EntityB beanB = new EntityB();
		beanB.setEmail("diaoyouwei@jd.com");
		beanADto.setEntityB(beanB);
		try {
			BeanUtils.copyProperties(beanA, beanADto);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(beanA.getEntityB().getEmail(),"diaoyouwei@jd.com");
	}
}
