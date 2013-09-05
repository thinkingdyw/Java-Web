package com.web.springmvc.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.dto.EntityADTO;
import com.web.commons.Result;
import com.web.commons.utils.HttpStatus;

@Controller
@RequestMapping("/sync/")
public class SyncController {
	
	private Logger logger = Logger.getLogger(SyncController.class);
	@RequestMapping("/index")
	public ModelAndView toIndex(){
		ModelAndView view = new ModelAndView();
		view.setViewName("views/index");
		return view;
	}
	
	@RequestMapping("/type_convert")
	@ResponseBody
	public Result<String> demo1(EntityADTO adto){
		//通用的相应结果对象，可以应对较快的需求变更
		Result<String> result = new Result<String>();
		result.setStatusCode(HttpStatus._2XX_200);
		result.setData("This is Response data!!!");
		result.setMsg("请求成功!");
		logger.info(adto.getDate());
		return result;
	}
}
