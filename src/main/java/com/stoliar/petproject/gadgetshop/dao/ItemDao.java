package com.stoliar.petproject.gadgetshop.dao;

import com.stoliar.petproject.gadgetshop.entity.Item;

import java.util.List;

public interface ItemDao {
    Item persist(Item item);

    Item modify(Item item);

    List<Item> findAllItems();


    List<Item> findItemsByQuery(String query);

    List<Item> findItemsByCategoryAndGroup(String category, String group);

    List<Item> findItemsByCategoryAndGroupAndQuery(String category, String group, String query);

    Item findItemById(Integer id);


    void deleteItem(Item item);

    List<Item> findItemsByGroup(String group);
}
