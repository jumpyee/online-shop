package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.CategoryPool;

import java.util.List;

public interface CategoryPoolService {
    CategoryPool findCategoryPoolById(Integer id);

    CategoryPool findCategoryPoolByName(String name);

    List<CategoryPool> findAllCategoryPools();

    CategoryPool saveCategoryPool(CategoryPool categoryPool);
}
