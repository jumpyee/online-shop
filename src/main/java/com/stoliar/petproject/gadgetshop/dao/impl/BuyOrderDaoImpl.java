package com.stoliar.petproject.gadgetshop.dao.impl;

import com.stoliar.petproject.gadgetshop.dao.BuyOrderDao;
import com.stoliar.petproject.gadgetshop.entity.BuyOrder;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyOrderDaoImpl implements BuyOrderDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public BuyOrder persist(BuyOrder buyOrder) {
        sessionFactory.getCurrentSession().persist(buyOrder);
        return buyOrder;
    }

    @Override
    public BuyOrder modify(BuyOrder buyOrder) {
        sessionFactory.getCurrentSession().update(buyOrder);
        return buyOrder;
    }

    @Override
    public void deleteBuyOrder(BuyOrder buyOrder) {
        sessionFactory.getCurrentSession().delete(buyOrder);
    }

    @Override
    public BuyOrder findBuyOrderById(Integer id) {
        return sessionFactory.getCurrentSession().find(BuyOrder.class, id);
    }

    @Override
    public List<BuyOrder> findAllBuyOrders() {
        return sessionFactory.getCurrentSession().createNamedQuery("findAllBuyOrders", BuyOrder.class).list();
    }
}
