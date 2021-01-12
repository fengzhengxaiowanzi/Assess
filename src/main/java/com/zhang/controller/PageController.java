package com.zhang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhang
 * @Description: com.zhang.controller
 * @Date：Created in 13:55 2021/1/6
 */
@RestController
public class PageController {
    @GetMapping("/")
    public ModelAndView defaultPage(){
        return new ModelAndView("userLogin");
    }
    @GetMapping("page.do")
    public ModelAndView refPage(String p){
        if(p == null || "".equals(p)){
            p = "userLogin";
        }
        return new ModelAndView(p);
    }
}
