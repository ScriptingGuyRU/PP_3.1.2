package com.boot.pp25.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user*")
public class UserController {

    @GetMapping
    public ModelAndView userMain(Authentication auth){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userName",auth.getName());
        modelAndView.setViewName("/userPages/userMainPage");
        return modelAndView;
    }
}
