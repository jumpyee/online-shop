package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.OrderedItem;

import java.util.List;

public interface OrderedItemDao {
    OrderedItem persist(OrderedItem item);

    List<OrderedItem> findAllOrderedItems();

    OrderedItem findOrderedItemById(Integer id);

    void deleteOrderedItem(OrderedItem item);
}
