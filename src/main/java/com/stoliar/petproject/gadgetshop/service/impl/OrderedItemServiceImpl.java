package com.stoliar.petproject.gadgetshop.service.impl;

import com.stoliar.petproject.gadgetshop.dao.OrderedItemDao;
import com.stoliar.petproject.gadgetshop.entity.OrderedItem;
import com.stoliar.petproject.gadgetshop.service.OrderedItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderedItemServiceImpl implements OrderedItemService {

    @Autowired
    private OrderedItemDao orderedItemDao;

    @Override
    public OrderedItem saveItem(OrderedItem orderedItem) {
        return orderedItemDao.persist(orderedItem);
    }


    @Override
    public List<OrderedItem> findAllOrderedItems() {
        return orderedItemDao.findAllOrderedItems();
    }

    @Override
    public OrderedItem findOrderedItemById(Integer id) {
        return orderedItemDao.findOrderedItemById(id);

    }

    @Override
    public boolean deleteOrderedItemById(Integer id) {
        OrderedItem item = orderedItemDao.findOrderedItemById(id);
        if (item != null) {
            orderedItemDao.deleteOrderedItem(item);
            return true;
        }
        return false;
    }
}
