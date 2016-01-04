package com.orgname.projectname.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.orgname.projectname.web.common.Result;
import com.orgname.projectname.web.common.controller.DefaultController;

/**
 * 异常处理测试
 * @author diaoyouwei
 *
 */
@Controller
@RequestMapping("/exp/")
public class ExcetionController extends DefaultController{

	/**
	 * 同步异常
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sync")
	public ModelAndView operation1()throws Exception{
		ModelAndView view = new ModelAndView("expView");
		//测试异常处理
		@SuppressWarnings("unused")
		int count = 1/0;
		return view;
	}
	/**
	 * 异步异常
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/async")
	public Result<String> operation2()throws Exception{
		Result<String> result = new Result<String>();
		//测试异常处理
		@SuppressWarnings("unused")
		int count = 1/0;
		return result;
	}
}
