package com.company.common.utils.validation.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Number {
	double max() default Double.MAX_VALUE;
	double min() default Double.MIN_VALUE;
	int[] range() default {};
}
