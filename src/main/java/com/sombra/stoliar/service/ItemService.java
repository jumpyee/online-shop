package com.sombra.stoliar.service;

import com.sombra.stoliar.entity.Category;
import com.sombra.stoliar.entity.Item;

import java.util.List;

public interface ItemService {


    Item saveItem(Item item);

    List<Item> findItemsByCategory(Category category);

    List<Item> findAllItems();
}
