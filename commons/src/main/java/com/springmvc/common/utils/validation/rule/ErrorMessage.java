package com.springmvc.common.utils.validation.rule;

public interface ErrorMessage {

	public final String REQUIRED = "%s是必填项!";
	public final String NOT_EMPTY = "%s不可为空!";
	public final String NOT_BLANK = "%s不可为空串!";
	public final String MAX_LENGTH = "%s最大长度为%d!";
	public final String MIN_LENGTH = "%s最小长度为%d!";
	public final String MAX = "%s最大值为%d!";
	public final String MIN = "%s最小值为%d!";
}
