package com.sombra.stoliar.controller;

import com.sombra.stoliar.model.UserLoginForm;
import com.sombra.stoliar.model.UserRegistrationForm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    public String exceptionHandler () {
        return "redirect:/404";
    }

}
