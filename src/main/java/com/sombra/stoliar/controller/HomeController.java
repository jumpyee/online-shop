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
                           @RequestParam(value = "group", required = false) String group,
                           @RequestParam(value = "query", required = false) String query, Model model) {
        List<Item> items;
        boolean categoryPresent = category != null && group != null;
        boolean queryPresent = query != null;

        if (categoryPresent && queryPresent) {
            model.addAttribute("group", group);
            model.addAttribute("category", category);
            model.addAttribute("query", query);
            items = itemService.findItemsByCategoryAndGroupAndQuery(category, group, query);
        } else if (queryPresent) {
            model.addAttribute("query", query);
            items = itemService.findItemsByQuery(query);
        } else if (categoryPresent) {
            model.addAttribute("group", group);
            model.addAttribute("category", category);
            items = itemService.findItemsByCategoryAndGroup(category, group);
        } else {
            items = itemService.findAllItems();
        }

        model.addAttribute("allItems", items);
        return "home/home";
    }


}
