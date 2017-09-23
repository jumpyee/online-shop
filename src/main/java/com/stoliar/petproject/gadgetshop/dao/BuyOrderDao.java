package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.BuyOrder;

import java.util.List;

public interface BuyOrderDao {
    BuyOrder persist(BuyOrder buyOrder);


    BuyOrder modify(BuyOrder buyOrder);

    void deleteBuyOrder(BuyOrder buyOrder);

    BuyOrder findBuyOrderById(Integer id);

    List<BuyOrder> findAllBuyOrders();
}
