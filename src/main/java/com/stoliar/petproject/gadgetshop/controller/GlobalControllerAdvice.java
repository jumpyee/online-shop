package com.stoliar.petproject.gadgetshop.controller;

import com.stoliar.petproject.gadgetshop.model.UserLoginForm;
import com.stoliar.petproject.gadgetshop.model.UserRegistrationForm;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(NoHandlerFoundException.class)
    public String exceptionHandler() {
        return "redirect:/404";
    }

}
