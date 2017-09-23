package com.stoliar.petproject.gadgetshop.service.impl;

import com.stoliar.petproject.gadgetshop.dao.BuyOrderDao;
import com.stoliar.petproject.gadgetshop.entity.BuyOrder;
import com.stoliar.petproject.gadgetshop.service.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuyOrderServiceImpl implements BuyOrderService {

    @Autowired
    private BuyOrderDao buyOrderDao;

    @Override
    public BuyOrder saveBuyOrder(BuyOrder buyOrder) {
        return buyOrderDao.persist(buyOrder);
    }

    @Override
    public BuyOrder findBuyOrderById(Integer id) {
        return buyOrderDao.findBuyOrderById(id);
    }

    @Override
    public void deleteBuyOrder(BuyOrder buyOrder) {
        buyOrderDao.deleteBuyOrder(buyOrder);
    }

    @Override
    public List<BuyOrder> findAllBuyOrders() {
        return buyOrderDao.findAllBuyOrders();
    }

    @Override
    public BuyOrder modifyBuyOrder(BuyOrder buyOrder) {
        return buyOrderDao.modify(buyOrder);
    }
}
