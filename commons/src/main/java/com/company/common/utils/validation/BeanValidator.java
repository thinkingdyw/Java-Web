package com.company.common.utils.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.company.common.utils.validation.constraints.Length;
import com.company.common.utils.validation.constraints.NotBlank;
import com.company.common.utils.validation.constraints.NotEmpty;
import com.company.common.utils.validation.constraints.NotNull;
import com.company.common.utils.validation.rule.ErrorMessage;
import com.company.common.utils.validation.rule.RuleUtils;
import com.company.web.domain.EntityA;

public final class BeanValidator implements Validator<Validateable> {

	@Override
	public ValidateResult validate(Validateable target) {
		Class<?> clazz = target.getClass();
		Method[] methods = getGetterMethods(clazz);
		ValidateResult result;
		try {
			result = validate(methods, target);
		} catch (Exception e) {
			result = new ValidateResult();
			result.setError(true);
		}
		return result;
	}

	private ValidateResult validate(Method[] methods, Validateable target)
			throws Exception {
		ValidateResult result = new ValidateResult();
		if (isEmpty(methods)) {
			return result;
		} else {
			for (Method method : methods) {
				Map<String, String> validRs = validate(method, target);
				if (null != validRs) {
					result.setError(true);
					result.getErrorMsg().putAll(validRs);
				}
			}
		}
		return result;
	}

	private Map<String, String> validate(Method method, Validateable target)
			throws Exception {
		Annotation[] rules = method.getAnnotations();
		Map<String, String> rs = validate(rules, method, target);
		return rs;
	}

	private Map<String, String> validate(Annotation[] rules, Method method,
			Validateable target) throws Exception {
		if (isEmpty(rules)) {
			return null;
		}
		Map<String, String> rs = new HashMap<String, String>();
		if (isPrimitiveObjectType(method.getReturnType())) {
			Object value = getValue(method, target);
			for (Annotation rule : rules) {
				if (rule.annotationType() == NotNull.class) {
					if (RuleUtils.isNull(value)) {
						NotNull notnull = (NotNull) rule;
						rs.put(getFieldName(method),
								String.format(ErrorMessage.REQUIRED,
										notnull.name()));
						return rs;
					}
				}
				if (rule.annotationType() == NotEmpty.class) {
					if (RuleUtils.isEmpty(RuleUtils.valueOf(value))) {
						NotEmpty empty = (NotEmpty) rule;
						rs.put(getFieldName(method),
								String.format(ErrorMessage.NOT_EMPTY,
										empty.name()));
						return rs;
					}
				}
				if (rule.annotationType() == NotBlank.class) {
					if (RuleUtils.isBlank(RuleUtils.valueOf(value))) {
						NotBlank blank = (NotBlank) rule;
						rs.put(getFieldName(method),
								String.format(ErrorMessage.NOT_BLANK,
										blank.name()));
						return rs;
					}
				}
				if (rule.annotationType() == Length.class) {
					Length length = (Length) rule;
					String val = RuleUtils.valueOf(value);
					if (RuleUtils.isEmpty(val)) {
						rs.put(getFieldName(method),
								String.format(ErrorMessage.NOT_EMPTY,
										length.name()));
						return rs;
					} else {
						if (length.max() != -1) {
							if (val.length() > length.max()) {
								rs.put(getFieldName(method), String.format(
										ErrorMessage.MAX_LENGTH, length.name(),
										length.max()));
								return rs;
							}
						}
						if (length.min() != -1) {
							if (val.length() < length.min()) {
								rs.put(getFieldName(method), String.format(
										ErrorMessage.MIN_LENGTH, length.name(),
										length.min()));
								return rs;
							}
						}
					}
				}
				if (rule.annotationType() == com.company.common.utils.validation.constraints.Number.class) {
					if (!RuleUtils.isNull(value)) {
						com.company.common.utils.validation.constraints.Number number = (com.company.common.utils.validation.constraints.Number) rule;
						double val = Double.valueOf(String.valueOf(value));
						if (number.max() != Double.MAX_VALUE) {
							if (val > number.max()) {
								rs.put(getFieldName(method),
										String.format(ErrorMessage.MAX,
												number.max()));
								return rs;
							}
						}
						if (number.min() != Double.MIN_VALUE) {
							if (val < number.min()) {
								rs.put(getFieldName(method),
										String.format(ErrorMessage.MIN,
												number.min()));
								return rs;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private String getFieldName(Method method) {
		String field = method.getName().substring(3);
		String fieldName = field.substring(0, 1).toLowerCase()
				+ field.substring(1);
		return fieldName;
	}

	private static Object getValue(Method method, Validateable target)
			throws Exception {
		return method.invoke(target);
	}

	private boolean isEmpty(Object[] list) {
		return list == null || list.length == 0;
	}

	/**
	 * get all getter
	 * 
	 * @param clazz
	 * @return
	 */
	private Method[] getGetterMethods(Class<?> clazz) {
		Method[] allMethod = clazz.getMethods();
		List<Method> getters = new ArrayList<Method>();
		if (allMethod != null && allMethod.length > 0) {
			for (Method method : allMethod) {
				if (isGetterAndWithOutParams(method)) {
					getters.add(method);
				}
			}
		}
		return getters.toArray(new Method[0]);
	}

	private boolean isGetterAndWithOutParams(Method method) {
		if (method.getName().startsWith("get")
				&& method.getParameterTypes().length == 0
				&& !method.getName().equals("getClass")) {
			if (hasConstraints(method.getAnnotations())) {
				return true;
			}
		}
		return false;
	}

	private boolean hasConstraints(Annotation[] annotations) {
		for (Annotation constraint : annotations) {
			if (constraint.annotationType() == NotNull.class) {
				return true;
			}
			if (constraint.annotationType() == NotEmpty.class) {
				return true;
			}
			if (constraint.annotationType() == NotBlank.class) {
				return true;
			}
			if (constraint.annotationType() == Length.class) {
				return true;
			}
			if (constraint.annotationType() == Number.class) {
				return true;
			}
		}
		return false;
	}

	private boolean isPrimitiveObjectType(Class<?> clazz) {
		if (clazz == Long.class) {
			return true;
		}
		if (clazz == Integer.class) {
			return true;
		}
		if (clazz == Double.class) {
			return true;
		}
		if (clazz == Short.class) {
			return true;
		}
		if (clazz == Float.class) {
			return true;
		}
		if (clazz == Boolean.class) {
			return true;
		}
		if (clazz == Byte.class) {
			return true;
		}
		if (clazz == Character.class) {
			return true;
		}
		if (clazz == String.class) {
			return true;
		}
		if (clazz == String.class) {
			return true;
		}
		if (clazz == Date.class) {
			return true;
		}
		return false;
	}
	

	@Test
	public  void t() {
		System.out.println(new BeanValidator().validate(new EntityA()).getErrorMsg());
	}
}
