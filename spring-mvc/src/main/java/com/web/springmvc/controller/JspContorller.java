package com.web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class JspContorller {

	@RequestMapping("/")
	public ModelAndView toIndex(ModelAndView view){
		view.setViewName("WEB-INF/views/index");
		return view;
	}
}
