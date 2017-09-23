package com.stoliar.petproject.gadgetshop.controller;


import com.stoliar.petproject.gadgetshop.entity.Item;
import com.stoliar.petproject.gadgetshop.entity.User;
import com.stoliar.petproject.gadgetshop.model.FormResponse;
import com.stoliar.petproject.gadgetshop.model.UserLoginForm;
import com.stoliar.petproject.gadgetshop.model.UserRegistrationForm;
import com.stoliar.petproject.gadgetshop.service.ItemService;
import com.stoliar.petproject.gadgetshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/async/user")
public class UserAjaxController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    @ResponseBody
    public FormResponse registerUser(@Validated @ModelAttribute("userRegistrationForm") UserRegistrationForm userForm, BindingResult result) {
        FormResponse formResponse = new FormResponse();

        if (result.hasErrors()) {
            formResponse.setStatus(false);
            formResponse.setResult(result.getAllErrors());
        } else {
            userService.registerUser(new User(userForm));
            formResponse.setStatus(true);
        }
        return formResponse;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public FormResponse loginUser(@Validated @ModelAttribute("userLoginForm") UserLoginForm userForm, BindingResult result) {
        FormResponse formResponse = new FormResponse();

        if (result.hasErrors()) {
            formResponse.setStatus(false);
            formResponse.setResult(result.getAllErrors());
        } else {
            boolean loginSuccess = userService.loginUser(userForm.getEmail(), userForm.getPassword());
            formResponse.setStatus(loginSuccess);
            if (!loginSuccess) {
                result.addError(new FieldError("invalidCredentials", "invalidCredentials", "Invalid email or password"));
                formResponse.setResult(result.getAllErrors());
            }
        }
        return formResponse;
    }

    @ResponseBody
    @RequestMapping(path = "/item/add_to_cart", method = RequestMethod.POST)
    public boolean addItemToCart(@RequestParam("id") Integer id, @RequestParam(value = "email", required = false) String email) {
        if (email == null) {
            return false;
        }
        User user = userService.findUserByEmail(email);
        Item item = itemService.findItemById(id);

        Integer amount = user.getCart().get(item);
        if (amount == null) {
            user.getCart().put(item, 1);
        } else {

            user.getCart().put(item, amount + 1);
        }
        userService.updateUser(user);
        return true;
    }

}
