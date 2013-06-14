package com.company.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.company.common.utils.validation.BeanValidator;
import com.company.common.utils.validation.ValidateResult;
import com.company.common.utils.validation.Validateable;
import com.company.common.utils.validation.Validator;
import com.company.common.utils.web.CookieUtils;
import com.company.common.utils.web.RequestUtils;
import com.company.web.domain.EntityA;
/**
 * 
 *@author thinkingdyw
 *----------------------------------
 * 2013-6-12 
 * email:thinkingdyw@gmail.com
 */
@Controller
@RequestMapping("/index")
public class HomeController {

	private static Logger LOG = Logger.getLogger(HomeController.class);

	@RequestMapping("/bean1")
	public ModelAndView toBean(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("views/index");
		try {
			EntityA bean = RequestUtils.toBean(EntityA.class, request);
			LOG.info("user name:"+bean.getName());
			view.addObject("bean", bean);
		} catch (Exception e) {
			LOG.warn("异常", e);
		}
		return view;
	}
	@RequestMapping("/bean2")
	public ModelAndView toBeanWithSubChild(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("views/index");
		try {
			EntityA bean = RequestUtils.toBean(EntityA.class, request);
			LOG.info("entity B name:"+bean.getB().getName());
			view.addObject("bean", bean);
		} catch (Exception e) {
			LOG.warn("异常", e);
		}
		return view;
	}
	@RequestMapping("/cookie")
	public ModelAndView cookie(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("views/index");
		try {
			CookieUtils.add("name", "thinkingdyw",response);
			LOG.info("cookie:"+CookieUtils.get("name", request).getValue());
		} catch (Exception e) {
			LOG.warn("异常", e);
		}
		return view;
	}
	@RequestMapping("/validate")
	public ModelAndView validate(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("views/index");
		try {
			EntityA bean = RequestUtils.toBean(EntityA.class, request);
			Validator<Validateable> validator = new BeanValidator();
			ValidateResult result = validator.validate(bean);
			LOG.info("user name:"+bean.getName());
			LOG.info("user name:"+result.getErrorMsg());
			if(result.hasError()){
				view.addObject("error", result.getErrorMsg());
			}else{
				view.addObject("bean", bean);
			}
		} catch (Exception e) {
			LOG.warn("异常", e);
		}
		return view;
	}
}
