package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.Category;

import java.util.List;

public interface CategoryService {
    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

    List<Category> findAllCategories();

    Category saveCategory(Category category);
}
