package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.OrderedItem;

import java.util.List;

public interface OrderedItemService {
    OrderedItem saveItem(OrderedItem orderedItem);

    List<OrderedItem> findAllOrderedItems();

    OrderedItem findOrderedItemById(Integer id);

    boolean deleteOrderedItemById(Integer id);
}
