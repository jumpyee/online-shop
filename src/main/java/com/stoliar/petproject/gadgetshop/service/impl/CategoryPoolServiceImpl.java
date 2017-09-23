package com.sombra.stoliar.service.impl;

import com.sombra.stoliar.dao.CategoryPoolDao;
import com.sombra.stoliar.entity.CategoryPool;
import com.sombra.stoliar.service.CategoryPoolService;
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
