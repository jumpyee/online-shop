package com.sombra.stoliar.controller;


import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String itemPage(@PathVariable(value = "id") Integer id, Model model) {
        Item item = itemService.findItemById(id);
        if (item != null) {
            model.addAttribute("item", item);
        }
        return "item/item";
    }


}
