package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.Item;

import java.util.List;

public interface ItemDao {
    Item persist(Item item);

    List findAllItems();

    List<Item> findItemsByCategory(String name);
}
