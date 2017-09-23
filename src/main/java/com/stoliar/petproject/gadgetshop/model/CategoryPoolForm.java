package com.stoliar.petproject.gadgetshop.model;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryPoolForm {
    @NotBlank(message = "Field can not be empty")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
