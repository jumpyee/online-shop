package com.sombra.stoliar.controller;


import com.sombra.stoliar.entity.CategoryPool;
import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.service.CategoryPoolService;
import com.sombra.stoliar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    CategoryPoolService categoryPoolService;

    @Autowired
    ItemService itemService;

    @ModelAttribute("allCategoryPools")
    public List<CategoryPool> getAllCategoryPools() {
        return categoryPoolService.findAllCategoryPools();
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String homePage(@RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "group", required = false) String group, Model model) {
        List<Item> items;
        if (category != null && group !=null) {
            items = itemService.findItemsByCategoryAndGroup(category,group);
        } else {
            items = itemService.findAllItems();
        }
        model.addAttribute("allItems",items);
        return "home/home";
    }


}
