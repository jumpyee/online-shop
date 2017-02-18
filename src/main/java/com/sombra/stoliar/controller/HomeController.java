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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private CategoryPoolService categoryPoolService;

    @Autowired
    private ItemService itemService;

    @ModelAttribute("allCategoryPools")
    public List<CategoryPool> getAllCategoryPools() {
        return categoryPoolService.findAllCategoryPools();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String homePage(@RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "group", required = false) String group,
                           @RequestParam(value = "query", required = false) String query,
                           @RequestParam(value = "page", required = false) Integer page,
                           Model model) {
        if (page == null) {
            page = 1;
        }
        int itemsOnPage = 12;

        List<Item> items;
        boolean groupPresent = group != null;
        boolean categoryAndGroupPresent = category != null && group != null;
        boolean queryPresent = query != null;

        if (categoryAndGroupPresent && queryPresent) {
            model.addAttribute("group", group);
            model.addAttribute("category", category);
            model.addAttribute("query", query);
            items = itemService.findItemsByCategoryAndGroupAndQuery(category, group, query);
        } else if (queryPresent) {
            model.addAttribute("query", query);
            items = itemService.findItemsByQuery(query);
        } else if (categoryAndGroupPresent) {
            model.addAttribute("group", group);
            model.addAttribute("category", category);
            items = itemService.findItemsByCategoryAndGroup(category, group);
        } else if (groupPresent) {
            model.addAttribute("group", group);
            items = itemService.findItemsByGroup(group);
        } else {
            items = itemService.findAllItems();
        }
        int pageAmount = (items.size() + itemsOnPage - 1) / itemsOnPage;
        items = itemService.getPagedItems(items, page, itemsOnPage);
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i < pageAmount + 1; i++) {
            pages.add(i);
        }
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("allItems", items);
        return "home/home";
    }

    @RequestMapping("/403")
    public String accessDeniedPage() {
        return "filter/403/403";
    }

    @RequestMapping("/authorize")
    public String notAuthorizedPage() {
        return "filter/not-authorized/not-authorized";
    }

    @RequestMapping("/404")
    public String noSuchPagePage() {
        return "filter/404/404";
    }

    @RequestMapping("/banned")
    public String userBannedPage() {
        return "filter/banned/banned";
    }

}
