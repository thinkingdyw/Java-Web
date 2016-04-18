package com.orgname.projectname.web.common.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.orgname.projectname.web.common.annotation.AvoidDuplicateSubmission;

/**
 * 不能解决分布式系统的防止表单提交，需要扩展
 * 防止表单重复提交拦截器
 * @author Administrator
 *
 */
public class AvoidDuplicateSubmissionTokenGernerator extends HandlerInterceptorAdapter{
    private Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionTokenGernerator.class);
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
                           throws Exception {
        HandlerMethod targetMethod = (HandlerMethod) handler;
        AvoidDuplicateSubmission descriptor = targetMethod.getMethodAnnotation(AvoidDuplicateSubmission.class);
        
        if(descriptor!=null){
            if(descriptor.gernateToken()){
                logger.info("generate form token");
                String token = UUID.randomUUID().toString();
                request.getSession().setAttribute(descriptor.token(), token);
            }
        }
    }
}
