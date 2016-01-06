package com.orgname.projectname.web.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class WebRequestUtils {

	private final static String AJAX_REQUEST = "XMLHttpRequest";
	private final static String AJAX_REQUEST_HEADER = "X-Requested-With";
	
	/**
	 * 是否Ajax异步请求
	 **/
	public static boolean isAsyncRequest(HttpServletRequest request){
		final String ajaxRequest = request.getHeader(AJAX_REQUEST_HEADER);
		if(null != ajaxRequest && AJAX_REQUEST.equals(ajaxRequest)){
			return true;
		}
		return false;
	}
	/**
	 * Ajax 方式返回相应信息
	 **/
	public static void asyncWrite(HttpServletResponse response,String content) throws IOException{
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(content);
	}
}
