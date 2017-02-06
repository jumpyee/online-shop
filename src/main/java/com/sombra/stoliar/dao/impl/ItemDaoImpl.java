package com.sombra.stoliar.dao.impl;

import com.sombra.stoliar.dao.ItemDao;
import com.sombra.stoliar.entity.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Item persist(Item item) {
        sessionFactory.getCurrentSession().persist(item);
        return item;
    }

    @Override
    public List findAllItems() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllItems", Item.class).list();
    }


    @Override
    public List<Item> findItemsByCategory(String name) {
        return sessionFactory.getCurrentSession().createNamedQuery("findItemsByCategory", Item.class).setParameter("categoryName", name)
                .list();
    }
}
