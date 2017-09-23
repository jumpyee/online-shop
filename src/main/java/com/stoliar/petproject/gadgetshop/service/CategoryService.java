package com.stoliar.petproject.gadgetshop.service;

import com.stoliar.petproject.gadgetshop.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

    List<Category> findAllCategories();

    Category saveCategory(Category category);
}
