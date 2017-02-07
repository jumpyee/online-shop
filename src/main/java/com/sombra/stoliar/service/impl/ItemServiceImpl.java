package com.sombra.stoliar.service.impl;

import com.sombra.stoliar.dao.ItemDao;
import com.sombra.stoliar.entity.Category;
import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item saveItem(Item item) {
        return itemDao.persist(item);
    }

    @Override
    public List<Item> findItemsByCategory(Category category) {
        return itemDao.findItemsByCategory(category.getName());
    }

    @Override
    public List<Item> findAllItems () {
        return itemDao.findAllItems();
    }
}
