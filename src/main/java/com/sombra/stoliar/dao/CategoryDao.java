package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

    Category persist(Category category);

    List<Category> findAllCategories();
}
