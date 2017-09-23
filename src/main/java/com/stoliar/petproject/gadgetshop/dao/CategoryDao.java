package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.Category;

import java.util.List;

public interface CategoryDao {
    Category findCategoryById(Integer id);

    Category findCategoryByName(String name);

    Category persist(Category category);

    List<Category> findAllCategories();
}
