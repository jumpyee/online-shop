package com.sombra.stoliar.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UserLoginForm {

    @NotBlank(message = "Field can not be empty")
    @Email
    private String email;

    @NotBlank(message = "Field can not be empty")
    private String password;

    public UserLoginForm() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
