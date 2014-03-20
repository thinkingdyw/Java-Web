package com.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * 默认情况下，是不会将异常信息打印到控制台或者日志，会将错误信息返回到页面，
 * 为了可以记录异常信息，所以需要重写logException方法
 * 
 * --------------------------------------------------
 * 也可以直接扩展：AbstractHandlerExceptionResolver，实现自己的处理逻辑
 * @author diaoyouwei
 *
 */
public class CustomMappingExceptionResolver extends SimpleMappingExceptionResolver{

	/**
	 * 此处可以写自己对异常信息的处理，比如记录统一的日志平台或者数据库、日志文件、控制台等,默认输出到控制台
	 */
	@Override
	protected void logException(Exception ex, HttpServletRequest request) {
		
		ex.printStackTrace();
	}
}
