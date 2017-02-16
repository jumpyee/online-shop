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
    public Item modify(Item item) {
        sessionFactory.getCurrentSession().update(item);
        return item;
    }

    @Override
    public List<Item> findAllItems() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllItems", Item.class).list();
    }

    @Override
    public List<Item> findItemsByQuery(String query) {
        return sessionFactory.getCurrentSession().createNamedQuery("findItemsByQuery", Item.class).setParameter("query", query)
                .list();
    }

    @Override
    public List<Item> findItemsByCategoryAndGroup(String category, String group) {
        return sessionFactory.getCurrentSession().createNamedQuery("findItemsByCategoryAndGroup", Item.class).setParameter("categoryName", category).setParameter("groupName", group)
                .list();
    }

    @Override
    public List<Item> findItemsByCategoryAndGroupAndQuery(String category, String group, String query) {
        return sessionFactory.getCurrentSession().createNamedQuery("findItemsByCategoryAndGroupAndQuery", Item.class).setParameter("categoryName", category).setParameter("groupName", group)
                .setParameter("query", query).list();
    }

    @Override
    public Item findItemById(Integer id) {
        return sessionFactory.getCurrentSession().get(Item.class, id);
    }

    @Override
    public void deleteItem(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }
}
