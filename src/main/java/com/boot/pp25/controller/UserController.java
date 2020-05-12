package com.boot.pp25.controller;

import com.boot.pp25.service.UserService;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView userGet(Authentication auth) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usersPage/users");

        //TODO изменить
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        modelAndView.addObject("userWithRoleUser",userService.findUserByUserName(auth.getName()));
        modelAndView.addObject("rolesAuth",roles.stream().map(Objects::toString).collect(Collectors.joining(" ")));
        //TODO До сюда

        return modelAndView;
    }
}
