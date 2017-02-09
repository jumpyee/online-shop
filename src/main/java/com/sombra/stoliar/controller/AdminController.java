package com.sombra.stoliar.controller;

import com.sombra.stoliar.entity.Category;
import com.sombra.stoliar.entity.CategoryPool;
import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.entity.User;
import com.sombra.stoliar.model.CategoryForm;
import com.sombra.stoliar.model.CategoryPoolForm;
import com.sombra.stoliar.model.ItemForm;
import com.sombra.stoliar.service.CategoryPoolService;
import com.sombra.stoliar.service.CategoryService;
import com.sombra.stoliar.service.ItemService;
import com.sombra.stoliar.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/user/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryPoolService categoryPoolService;

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @ModelAttribute("allCategoryPools")
    public List<CategoryPool> getAllCategoryPools() {
        return categoryPoolService.findAllCategoryPools();
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @ModelAttribute("categoryForm")
    public CategoryForm getCategoryForm() {
        return new CategoryForm();
    }

    @ModelAttribute("categoryPoolForm")
    public CategoryPoolForm getCategoryPoolForm() {
        return new CategoryPoolForm();
    }

    @ModelAttribute("itemForm")
    public ItemForm getItemForm() {
        return new ItemForm();
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String adminPage(Model model) {
        return "admin/admin";
    }

    @RequestMapping(value = "/add_categorypool", method = RequestMethod.POST)
    public String addGroup(@Validated @ModelAttribute("categoryPoolForm") CategoryPoolForm categoryPoolForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("addGroupTab", true);
            return "admin/admin";
        }
        String name = categoryPoolForm.getName();
        categoryPoolService.saveCategoryPool(new CategoryPool(name));
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/add_category", method = RequestMethod.POST)
    public String addCategory(@Validated @ModelAttribute("categoryForm") CategoryForm categoryForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("addCategoryTab", true);
            return "admin/admin";
        }
        String name = categoryForm.getName();
        CategoryPool categoryPool = categoryPoolService.findCategoryPoolById(categoryForm.getCategoryPoolId());
        categoryService.saveCategory(new Category(name, categoryPool));
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/add_item", method = RequestMethod.POST)
    public String addItem(@Validated @ModelAttribute("itemForm") ItemForm itemForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("addItemTab", true);
            return "admin/admin";
        }
        String name = itemForm.getName();
        Double price = itemForm.getPrice();
        String description = itemForm.getDescription();
        String imageReference="images/"+itemForm.getImage().getOriginalFilename();

        Category category = categoryService.findCategoryById(itemForm.getCategoryId());

        try {
            saveFile(itemForm.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        itemService.saveItem(new Item(name, price, description, category,imageReference));
        return "redirect:/user/admin";
    }


    private void saveFile(MultipartFile multipartFile) throws Exception {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get("C://Users//Taras Stolyar//IdeaProjects//OnlineShop//src//main//webapp//images//" + multipartFile.getOriginalFilename());
        Files.write(path, bytes);
    }
}
