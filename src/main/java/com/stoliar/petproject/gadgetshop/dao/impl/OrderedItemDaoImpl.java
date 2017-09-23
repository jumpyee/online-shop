package com.sombra.stoliar.dao.impl;

import com.sombra.stoliar.dao.OrderedItemDao;
import com.sombra.stoliar.entity.OrderedItem;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderedItemDaoImpl implements OrderedItemDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public OrderedItem persist(OrderedItem orderedItem) {
        sessionFactory.getCurrentSession().persist(orderedItem);
        return orderedItem;
    }


    @Override
    public List<OrderedItem> findAllOrderedItems() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllItems", OrderedItem.class).list();
    }


    @Override
    public OrderedItem findOrderedItemById(Integer id) {
        return sessionFactory.getCurrentSession().get(OrderedItem.class, id);
    }

    @Override
    public void deleteOrderedItem(OrderedItem item) {
        sessionFactory.getCurrentSession().delete(item);
    }
}
