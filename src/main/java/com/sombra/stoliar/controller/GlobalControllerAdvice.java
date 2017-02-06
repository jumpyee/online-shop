package com.sombra.stoliar.controller;

import com.sombra.stoliar.model.UserLoginForm;
import com.sombra.stoliar.model.UserRegistrationForm;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("userLoginForm")
    public UserLoginForm getUserLoginForm() {
        return new UserLoginForm();
    }

    @ModelAttribute("userRegistrationForm")
    public UserRegistrationForm getUserRegistrationForm() {
        return new UserRegistrationForm();
    }

}
