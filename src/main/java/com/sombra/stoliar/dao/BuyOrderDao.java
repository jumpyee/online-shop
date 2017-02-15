package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.BuyOrder;

import java.util.List;

public interface BuyOrderDao {
    BuyOrder persist(BuyOrder buyOrder);


    BuyOrder modify(BuyOrder buyOrder);

    void deleteBuyOrder(BuyOrder buyOrder);

    BuyOrder findBuyOrderById(Integer id);

    List<BuyOrder> findAllBuyOrders();
}
