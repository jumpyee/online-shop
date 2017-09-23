package com.stoliar.petproject.gadgetshop.model;


import com.stoliar.petproject.gadgetshop.validation.UniqueEmail;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRegistrationForm {

    @NotBlank(message = "Field can not be empty")
    private String name;

    @NotBlank(message = "Field can not be empty")
    private String surname;

    @NotBlank(message = "Field can not be empty")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Field can not be empty")
    @Size(min = 6, message = "At least 6 symbols")
    private String password;

    @NotBlank(message = "Field can not be empty")
    @Pattern(regexp = "\\+380\\d{9}", message = "Phone must match pattern +380XXXXXXXXX")
    private String phoneNumber;


    public UserRegistrationForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
