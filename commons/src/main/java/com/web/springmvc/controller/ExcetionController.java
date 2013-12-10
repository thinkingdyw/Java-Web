package com.web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理测试
 * @author diaoyouwei
 *
 */
@Controller
@RequestMapping("/exp/")
public class ExcetionController {

	@RequestMapping("/")
	public ModelAndView operation()throws Exception{
		ModelAndView view = new ModelAndView("expView");
		//测试异常处理
		@SuppressWarnings("unused")
		int count = 1/0;
		return view;
	}
}
