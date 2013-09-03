package com.web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.commons.Result;
import com.web.commons.utils.HttpStatus;

/**
 * Ajax异步请求控制器
 * @author diaoyouwei
 *
 */
@Controller
@RequestMapping("/ajax/")
public class AjaxController {
	/**
	 * Ajax请求返回JSON格式结果
	 * @return
	 */
	@RequestMapping("/json_result")
	@ResponseBody
	public Result<String> demo1(){
		//通用的相应结果对象，可以应对较快的需求变更
		Result<String> result = new Result<String>();
		result.setStatusCode(HttpStatus._2XX_200);
		result.setMsg("请求成功!");
		return result;
	}
}