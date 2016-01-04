package com.orgname.projectname.web.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 自定义拦截器
 * @author diaoyouwei
 *
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	    logger.info("测试登录拦截器:OK!测试通过。");
		return super.preHandle(request, response, handler);
	}
}
