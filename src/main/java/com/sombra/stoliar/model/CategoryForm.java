package com.sombra.stoliar.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CategoryForm {
    @NotBlank(message = "Field can not be empty")
    private String name;
    @NotNull(message = "You have to choose group")
    private Integer categoryPoolId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryPoolId() {
        return categoryPoolId;
    }

    public void setCategoryPoolId(Integer categoryPoolId) {
        this.categoryPoolId = categoryPoolId;
    }
}
