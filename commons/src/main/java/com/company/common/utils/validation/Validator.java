package com.company.common.utils.validation;

public interface Validator<T> {

	/**
	 * 
	 * @param t 校验的目标对象
	 * @return
	 */
	public ValidateResult validate(T target);
}
