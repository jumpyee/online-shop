package com.stoliar.petproject.gadgetshop.controller;

import com.stoliar.petproject.gadgetshop.service.ItemService;
import com.stoliar.petproject.gadgetshop.service.UserService;
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


