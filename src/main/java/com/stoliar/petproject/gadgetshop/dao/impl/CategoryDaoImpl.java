package com.sombra.stoliar.dao.impl;

import com.sombra.stoliar.dao.CategoryDao;
import com.sombra.stoliar.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category findCategoryById(Integer id) {
        return sessionFactory.getCurrentSession().find(Category.class, id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return sessionFactory.getCurrentSession().createNamedQuery("findCategoryByName", Category.class).setParameter("name", name)
                .list().get(0);
    }

    @Override
    public Category persist(Category category) {
        sessionFactory.getCurrentSession().persist(category);
        return category;
    }

    @Override
    public List<Category> findAllCategories() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllCategories", Category.class).list();
    }
}
