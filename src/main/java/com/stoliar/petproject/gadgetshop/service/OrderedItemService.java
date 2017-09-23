package com.stoliar.petproject.gadgetshop.service;

import com.stoliar.petproject.gadgetshop.entity.OrderedItem;

import java.util.List;

public interface OrderedItemService {
    OrderedItem saveItem(OrderedItem orderedItem);

    List<OrderedItem> findAllOrderedItems();

    OrderedItem findOrderedItemById(Integer id);

    boolean deleteOrderedItemById(Integer id);
}
