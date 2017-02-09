package com.sombra.stoliar.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/item")
public class ItemController {

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String itemPage(@RequestParam ("id") Integer id, Model model) {
        // check if present by this id
        return "item/item";
    }


}
