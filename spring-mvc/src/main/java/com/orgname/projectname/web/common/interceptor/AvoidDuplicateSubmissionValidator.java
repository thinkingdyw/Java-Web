package com.orgname.projectname.web.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.orgname.projectname.web.common.annotation.AvoidDuplicateSubmission;

/**
 * 不能解决分布式系统的防止表单提交，需要扩展
 * 防止表单重复提交拦截器
 * @author Administrator
 *
 */
public class AvoidDuplicateSubmissionValidator extends HandlerInterceptorAdapter{
    private Logger logger = LoggerFactory.getLogger(AvoidDuplicateSubmissionValidator.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        logger.info("validate form duplicate submission");
        HandlerMethod targetMethod = (HandlerMethod) handler;
        AvoidDuplicateSubmission descriptor = targetMethod.getMethodAnnotation(AvoidDuplicateSubmission.class);
        
        if(descriptor!=null){
            String clientToken = request.getParameter(descriptor.token());
            if(StringUtils.isEmpty(clientToken)){
                logger.info("illegal form token,don't submit form!");
                return false;
            }else{
                HttpSession seesion = request.getSession(true);
                synchronized (this) {
                    String serverToken = (String) seesion.getAttribute(descriptor.token());
                    if(clientToken.equals(serverToken)){
                        seesion.removeAttribute(descriptor.token());
                        return true;
                    }else{
                        logger.info(request.getServletPath()+" duplicate submission!");
                        response.sendRedirect(request.getHeader("referer"));
                        return false;
                    }
                }
            }
        }
        return true;
    }    
}
