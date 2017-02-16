package com.sombra.stoliar.controller;

import com.sombra.stoliar.service.ItemService;
import com.sombra.stoliar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/async/user/admin")
public class AdminAjaxController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/ban_user", method = RequestMethod.POST)
    public boolean banUser(@RequestParam("email") String email) {
        return userService.banUser(email);
    }

    @ResponseBody
    @RequestMapping(value = "/unban_user", method = RequestMethod.POST)
    public boolean unbanUser(@RequestParam("email") String email) {
        return userService.unbanUser(email);
    }

    @ResponseBody
    @RequestMapping(value = "/delete_item", method = RequestMethod.POST)
    public boolean deleteItem(@RequestParam("id") Integer id) {
        return itemService.deleteItemById(id);
    }

}
