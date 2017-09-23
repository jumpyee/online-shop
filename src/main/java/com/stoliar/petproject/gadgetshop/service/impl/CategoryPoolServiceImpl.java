package com.stoliar.petproject.gadgetshop.service.impl;

import com.stoliar.petproject.gadgetshop.dao.CategoryPoolDao;
import com.stoliar.petproject.gadgetshop.entity.CategoryPool;
import com.stoliar.petproject.gadgetshop.service.CategoryPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryPoolServiceImpl implements CategoryPoolService {

    @Autowired
    private CategoryPoolDao categoryPoolDao;

    @Override
    public CategoryPool findCategoryPoolById(Integer id) {
        return categoryPoolDao.findCategoryPoolById(id);
    }

    @Override
    public CategoryPool findCategoryPoolByName(String name) {
        return categoryPoolDao.findCategoryPoolByName(name);
    }

    @Override
    public List<CategoryPool> findAllCategoryPools() {
        return categoryPoolDao.findAllCategoryPools();
    }

    @Override
    public CategoryPool saveCategoryPool(CategoryPool categoryPool) {
        return categoryPoolDao.persist(categoryPool);
    }
}
