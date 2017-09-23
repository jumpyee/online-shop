package com.stoliar.petproject.gadgetshop.dao.impl;

import com.stoliar.petproject.gadgetshop.dao.CategoryPoolDao;
import com.stoliar.petproject.gadgetshop.entity.CategoryPool;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryPoolDaoImpl implements CategoryPoolDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public CategoryPool findCategoryPoolById(Integer id) {
        return sessionFactory.getCurrentSession().find(CategoryPool.class, id);
    }

    @Override
    public CategoryPool findCategoryPoolByName(String name) {
        return sessionFactory.getCurrentSession().createNamedQuery("findCategoryPoolByName", CategoryPool.class).setParameter("name", name)
                .list().get(0);
    }

    @Override
    public CategoryPool persist(CategoryPool categoryPool) {
        sessionFactory.getCurrentSession().persist(categoryPool);
        return categoryPool;
    }

    @Override
    public List<CategoryPool> findAllCategoryPools() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllCategoryPools", CategoryPool.class).list();
    }
}
