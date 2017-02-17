package com.sombra.stoliar.controller;


import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.entity.User;
import com.sombra.stoliar.model.FormResponse;
import com.sombra.stoliar.model.UserLoginForm;
import com.sombra.stoliar.model.UserRegistrationForm;
import com.sombra.stoliar.service.ItemService;
import com.sombra.stoliar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/async/user")
public class UserAjaxController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

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
        session.setAttribute("authenticatedUser", user);
        return true;
    }

}
