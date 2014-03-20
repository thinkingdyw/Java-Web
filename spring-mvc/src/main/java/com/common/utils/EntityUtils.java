package com.common.utils;

import java.lang.reflect.Constructor;

public final class EntityUtils {
	
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
