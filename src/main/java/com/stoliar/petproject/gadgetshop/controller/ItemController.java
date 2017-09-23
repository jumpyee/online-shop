package com.stoliar.petproject.gadgetshop.controller;


import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.service.ItemService;
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
