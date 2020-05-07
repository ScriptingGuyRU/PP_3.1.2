package com.boot.pp25.controller;

import com.boot.pp25.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class loginController {

    UserService userService;

    @Autowired
    public loginController(UserService userService) {
        this.userService = userService;
    }

}
