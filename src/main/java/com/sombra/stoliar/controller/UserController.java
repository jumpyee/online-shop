package com.sombra.stoliar.controller;

import com.sombra.stoliar.service.ItemService;
import com.sombra.stoliar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/logout")
    public String logoutUser() {
        userService.logoutUser();
        return "redirect:/";
    }
}


