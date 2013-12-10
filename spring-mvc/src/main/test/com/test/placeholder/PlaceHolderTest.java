package com.test.placeholder;

import java.util.Properties;
import org.junit.Test;
import com.web.commons.resolver.PlaceholderResolver;
import com.web.commons.resolver.PropertiesPlaceholderResolver;


public class PlaceHolderTest {

	@Test
	public void testResoveProperties(){
		PlaceholderResolver resolver = new PropertiesPlaceholderResolver();
		
		Properties props = new Properties();
		
		props.setProperty("user.name", "dyw");
		props.setProperty("greeting", "Hello ${user.name}!");
		
		System.out.println(resolver.resolve(props));
	}
}
