package com.orgname.projectname.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.orgname.projectname.web.common.annotation.AvoidDuplicateSubmission;
import com.orgname.projectname.web.common.controller.DefaultController;

@Controller
@Scope("prototype")
@RequestMapping("/sync")
public class NormalController extends DefaultController{

    @RequestMapping("/nolayout")
    public ModelAndView noUseLayOut(){
        return new ModelAndView("views/no_use_layout");
    }
    
    /**
     * 生成防止表单提交的token
     * @return
     */
    @RequestMapping("/form")
    @AvoidDuplicateSubmission(token="token",gernateToken=true)
    public ModelAndView gernateToken(){
        return new ModelAndView("views/form");
    }
    /**
     * 防止表单重复提交
     * @return
     */
    @RequestMapping(value="/avoidDuplicateSubmission",method=RequestMethod.POST)
    @ResponseBody
    @AvoidDuplicateSubmission(token="token")
    public String avoidFormDeuplicateSubmit(HttpServletResponse response){
       return "表单没有重复提交";
    }
}
