package com.orgname.projectname.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.orgname.projectname.web.common.controller.DefaultController;

@Controller
public class LoginController extends DefaultController{

    /**
     * 跳转到首页
     * @return 首页
     */
    @RequestMapping(value={"","/","/index.html","/index.htm"})
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("views/index");
        return index;
    }
    /**
     * 登录
     * @return 首页
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public ModelAndView login(){
        ModelAndView index = new ModelAndView("redirect:/");
        return index;
    }
    /**
     * 注销
     * @return 登录页
     */
    @RequestMapping("/logout")
    public ModelAndView logout(){
        ModelAndView index = new ModelAndView("views/login");
        return index;
    }
}
