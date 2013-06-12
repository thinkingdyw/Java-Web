package com.company.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.company.common.utils.web.CookieUtils;
import com.company.common.utils.web.RequestUtils;
import com.company.web.User;

@Controller
@RequestMapping("/index/")
public class HomeController {

	private static Logger LOG = Logger.getLogger(HomeController.class);

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("index");
		try {
			User user = RequestUtils.toBean(User.class, request);
			//CookieUtils.add("name", "diaoyouwei--", response);
			CookieUtils.add("name", "diaoyouwei", response);
			System.out.println(CookieUtils.get("name", request).getValue());
			view.addObject("p", user);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.warn("异常", e);
		}
		return view;
	}
}
