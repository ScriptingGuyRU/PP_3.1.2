package com.boot.pp25.controller;

import antlr.StringUtils;
import com.boot.pp25.model.Role;
import com.boot.pp25.model.User;
import com.boot.pp25.service.RoleServices;
import com.boot.pp25.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class adminController {

    UserService userService;

    RoleServices roleServices;

    Logger logger = LoggerFactory.getLogger(adminController.class);

    @Autowired
    public adminController(UserService userService, RoleServices roleServices) {
        this.userService = userService;
        this.roleServices = roleServices;
    }

    @GetMapping
    public ModelAndView mainAdminControllerGet(Authentication auth){
        ModelAndView mv = new ModelAndView();
//        получение списка ролей для помещения в navbar
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        //Временный секьюрити
        if (roles.contains("ADMIN")){
            mv.addObject("userIsAdmin",true);
        }



        mv.addObject("users",userService.findAll());
        mv.addObject("userNameAuth",auth.getName());
        mv.addObject("rolesAuth",roles.stream().map(Objects::toString).collect(Collectors.joining(" ")));
        mv.addObject("user", new User()); //для Thymeleaf нужно передать объект
        mv.setViewName("adminPages/adminMainPageBootStrap");
        return mv;
    }

    @PostMapping("/add")
    public ModelAndView addUserControllerPost(@ModelAttribute("user") @Valid User user,
                                              BindingResult result,
                                              @RequestParam(value = "rolesFromHtml") String[] rolesFromHtml) {
        ModelAndView mv = new ModelAndView();
        if (result.hasErrors()){
            logger.error(String.valueOf(result.getAllErrors()));
            mv.setViewName("adminPages/adminMainPageBootStrap");
            return mv;
        }

        Set<Role> userRoles = new HashSet<>();
        for (String role: rolesFromHtml ) {
            if (role.equals("user")) {
                userRoles.add(roleServices.getRoleByName("USER"));
            }
            if (role.equals("admin")) {
                userRoles.add(roleServices.getRoleByName("ADMIN"));
            }
        }

        user.setRoles(userRoles);
        userService.saveUser(user);
        logger.info("Add User: " + user.toString());
        mv.setViewName("redirect:/admin");
        return mv;
    }

    @PostMapping("/edit")
    public ModelAndView editUserControllerPost(@ModelAttribute @Valid User user,
                                               BindingResult result,
                                               @RequestParam(value = "rolesFromHtml") String[] rolesFromHtml) {
        ModelAndView mv = new ModelAndView();

        if(result.hasErrors()){
            mv.setViewName("adminPages/editUserPage");
            return mv;
        }

        Set<Role> userRoles = new HashSet<>();
        for (String role: rolesFromHtml ) {
            if (role.equals("user")) {
                userRoles.add(roleServices.getRoleByName("USER"));
            }
            if (role.equals("admin")) {
                userRoles.add(roleServices.getRoleByName("ADMIN"));
            }
        }
        user.setRoles(userRoles);
        userService.saveUser(user);
        mv.setViewName("redirect:/admin");
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return "redirect:/admin";
    }

}
