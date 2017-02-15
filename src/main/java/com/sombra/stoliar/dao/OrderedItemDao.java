package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.OrderedItem;

import java.util.List;

public interface OrderedItemDao {
    OrderedItem persist(OrderedItem item);

    List<OrderedItem> findAllOrderedItems();

    OrderedItem findOrderedItemById(Integer id);

    void deleteOrderedItem(OrderedItem item);
}
