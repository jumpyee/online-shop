package com.sombra.stoliar.controller;

import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String cartPage (@RequestParam(value = "email", required = false) String email,
                            Model model) {
        return "cart/cart";
    }
}
