package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.CategoryPool;

import java.util.List;

public interface CategoryPoolDao {
    CategoryPool findCategoryPoolById(Integer id);

    CategoryPool findCategoryPoolByName(String name);

    CategoryPool persist(CategoryPool categoryPool);

    List<CategoryPool> findAllCategoryPools();
}
