package com.stoliar.petproject.gadgetshop.service;

import com.stoliar.petproject.gadgetshop.entity.CategoryPool;

import java.util.List;

public interface CategoryPoolService {
    CategoryPool findCategoryPoolById(Integer id);

    CategoryPool findCategoryPoolByName(String name);

    List<CategoryPool> findAllCategoryPools();

    CategoryPool saveCategoryPool(CategoryPool categoryPool);
}
