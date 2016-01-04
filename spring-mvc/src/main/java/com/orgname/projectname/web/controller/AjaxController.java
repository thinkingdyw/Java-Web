package com.orgname.projectname.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.orgname.projectname.web.common.Result;
import com.orgname.projectname.web.common.controller.DefaultController;

/**
 * 异步请求控制器
 * @author diaoyouwei
 *
 */
@Controller
@RequestMapping("/async/")
public class AjaxController extends DefaultController{
	/**
	 * Ajax请求返回JSON格式结果
	 * @return
	 */
	@RequestMapping("/ajax")
	@ResponseBody //说明返回JSON串
	public Result<String> demo1(){
		//通用的响应结果对象，可以应对较快的需求变更
		Result<String> result = new Result<String>();
		result.setData("This is Response data!!!");
		result.success("success");
		return result;
	}
}
