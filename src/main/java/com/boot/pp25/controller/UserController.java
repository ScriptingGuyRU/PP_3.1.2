package com.boot.pp25.controller;

import com.boot.pp25.model.Role;
import com.boot.pp25.model.User;
import com.boot.pp25.service.abstractServ.RoleServices;
import com.boot.pp25.service.abstractServ.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private RoleServices roleServices;

    @Autowired
    public UserController(UserService userService, RoleServices roleServices) {
        this.userService = userService;
        this.roleServices = roleServices;
    }

    @GetMapping
    public ModelAndView userGet(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersPage/users");
        modelAndView.addObject("user",auth.getPrincipal());
        modelAndView.addObject("rolesAuth",((User) auth.getPrincipal()).getRoles()
                .stream().map(Objects::toString).collect(Collectors.joining(" ")));
        return modelAndView;
    }
}
