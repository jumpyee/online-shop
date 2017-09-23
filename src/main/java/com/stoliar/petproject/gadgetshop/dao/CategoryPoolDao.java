package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.CategoryPool;

import java.util.List;

public interface CategoryPoolDao {
    CategoryPool findCategoryPoolById(Integer id);

    CategoryPool findCategoryPoolByName(String name);

    CategoryPool persist(CategoryPool categoryPool);

    List<CategoryPool> findAllCategoryPools();
}
