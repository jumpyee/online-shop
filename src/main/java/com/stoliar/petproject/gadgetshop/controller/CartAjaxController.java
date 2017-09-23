package com.stoliar.petproject.gadgetshop.controller;


import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.service.ItemService;
import com.stoliar.petproject.gadgetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/async/cart")
public class CartAjaxController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/delete_item")
    @ResponseBody
    public boolean deleteItem(@RequestParam("id") Integer id, @RequestParam("email") String email) {
        User user = userService.findUserByEmail(email);
        user.getCart().remove(itemService.findItemById(id));
        userService.updateUser(user);
        return true;
    }

    @RequestMapping(value = "/change_item_amount")
    @ResponseBody
    public boolean changeItemAmount(@RequestParam("id") Integer id, @RequestParam("email") String email, @RequestParam("amount") Integer amount) {
        User user = userService.findUserByEmail(email);
        user.getCart().put(itemService.findItemById(id), amount);
        userService.updateUser(user);
        return true;
    }

}
