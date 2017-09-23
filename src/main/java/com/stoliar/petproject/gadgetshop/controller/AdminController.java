package com.stoliar.petproject.gadgetshop.controller;

import com.stoliar.petproject.gadgetshop.entity.*;
import com.stoliar.petproject.gadgetshop.model.CategoryForm;
import com.stoliar.petproject.gadgetshop.model.CategoryPoolForm;
import com.stoliar.petproject.gadgetshop.model.ItemForm;
import com.stoliar.petproject.gadgetshop.model.ItemModifyForm;
import com.stoliar.petproject.gadgetshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/user/admin")
public class AdminController {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private BuyOrderService buyOrderService;

    @Autowired
    private CategoryPoolService categoryPoolService;

    @ModelAttribute("allUsers")
    public List<User> getAllUsers() {
        List<User> users = userService.findAllUsers();
        users.remove(userService.getAuthenticatedUser());
        return users;
    }

    @ModelAttribute("allBuyOrders")
    public List<BuyOrder> getAllBuyOrders() {
        return buyOrderService.findAllBuyOrders();
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
    public String adminPage() {
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
        Category category = categoryService.findCategoryById(itemForm.getCategoryId());
        String imageReference = null;
        MultipartFile image = itemForm.getImage();
        if (imagesService.isValidImage(image)) {
            imageReference = imagesService.saveImage(itemForm.getImage());
        }
        itemService.saveItem(new Item(name, price, description, category, imageReference));
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/modify_item/{id}", method = RequestMethod.GET)
    public String modifyPage(@PathVariable("id") Integer id, Model model) {
        Item item = itemService.findItemById(id);
        ItemModifyForm itemModifyForm = new ItemModifyForm();
        itemModifyForm.setId(item.getId());
        itemModifyForm.setName(item.getName());
        itemModifyForm.setDescription(item.getDescription());
        itemModifyForm.setPrice(item.getPrice());
        itemModifyForm.setImageReference(item.getImageReference());
        itemModifyForm.setCategoryId(item.getCategory().getId());
        model.addAttribute("itemModifyForm", itemModifyForm);
        return "modify-item/item";
    }

    @RequestMapping(value = "/modify_item", method = RequestMethod.POST)
    public String modifyItem(@Validated @ModelAttribute("itemModifyForm") ItemModifyForm itemModifyForm, BindingResult result) {
        if (result.hasErrors()) {
            return "modify-item/item";
        }
        Item item = itemService.findItemById(itemModifyForm.getId());
        item.setName(itemModifyForm.getName());
        item.setPrice(itemModifyForm.getPrice());
        item.setDescription(itemModifyForm.getDescription());

        Category category = categoryService.findCategoryById(itemModifyForm.getCategoryId());
        item.setCategory(category);

        MultipartFile image = itemModifyForm.getImage();
        if (imagesService.isValidImage(image)) {
            String imageReference = imagesService.saveImage(itemModifyForm.getImage());
            item.setImageReference(imageReference);
        } else {
            item.setImageReference(itemModifyForm.getImageReference());
        }
        itemService.modifyItem(item);
        return "redirect:/";
    }

}
