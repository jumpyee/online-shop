package com.sombra.stoliar.service.impl;

import com.sombra.stoliar.dao.CategoryDao;
import com.sombra.stoliar.entity.Category;
import com.sombra.stoliar.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Category findCategoryById(Integer id) {
        return categoryDao.findCategoryById(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryDao.findCategoryByName(name);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryDao.persist(category);
    }
}
