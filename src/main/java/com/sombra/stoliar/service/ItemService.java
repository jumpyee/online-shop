package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.Item;

import java.util.List;

public interface ItemService {


    Item saveItem(Item item);

    Item modifyItem(Item item);

    List<Item> findItemsByCategoryAndGroup(String category, String group);

    List<Item> findAllItems();

    Item findItemById(Integer id);

    boolean deleteItemById(Integer id);

    List<Item> findItemsByQuery(String query);

    List<Item> findItemsByCategoryAndGroupAndQuery(String category, String group, String query);

    List<Item> getPagedItems(List<Item> items, Integer page, Integer itemsOnPage);
}
