package com.sombra.stoliar.dao;

import com.sombra.stoliar.entity.Item;

import java.util.List;

public interface ItemDao {
    Item persist(Item item);

    List<Item> findAllItems();



    List<Item> findItemsByCategoryAndGroup(String category, String group);

    Item findItemById(Integer id);



    void deleteItem(Item item);
}
