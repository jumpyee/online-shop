package com.stoliar.petproject.gadgetshop.service.impl;

import com.stoliar.petproject.gadgetshop.dao.CategoryDao;
import com.stoliar.petproject.gadgetshop.entity.Category;
import com.stoliar.petproject.gadgetshop.service.CategoryService;
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
