package com.orgname.projectname.web.common.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.orgname.projectname.core.dictionary.DataDictionaryConfiguration;


/**
 * 数据字典
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class DictionaryController {
    
    private Logger logger = LoggerFactory.getLogger(DictionaryController.class);
    
    @Autowired private DataDictionaryConfiguration dicConfig;
    
    @Cacheable(value="default",key="#root.methodName")
    @RequestMapping("/dic")
    @ResponseBody
    public String dictionary(){
        logger.info("测试缓存，没有使用缓存");
        Map<String, Map<String, Object>> dic = dicConfig.get();
        return "window."+dicConfig.getRoot()+"="+JSON.toJSONString(dic);
    }
}
