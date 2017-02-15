package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.BuyOrder;

import java.util.List;

public interface BuyOrderService {
    BuyOrder saveBuyOrder(BuyOrder buyOrder);

    BuyOrder findBuyOrderById(Integer id);

    void deleteBuyOrder(BuyOrder buyOrder);

    List<BuyOrder> findAllBuyOrders();

    BuyOrder modifyBuyOrder(BuyOrder buyOrder);
}
