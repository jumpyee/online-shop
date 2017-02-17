package com.sombra.stoliar.service.impl;

import com.sombra.stoliar.dao.ItemDao;
import com.sombra.stoliar.entity.Item;
import com.sombra.stoliar.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
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
    public Item modifyItem(Item item) {
        return itemDao.modify(item);
    }

    @Override
    public List<Item> findAllItems() {
        return itemDao.findAllItems();
    }

    @Override
    public Item findItemById(Integer id) {
        return itemDao.findItemById(id);

    }

    @Override
    public boolean deleteItemById(Integer id) {
        Item item = itemDao.findItemById(id);
        if (item != null) {
            itemDao.deleteItem(item);
            return true;
        }
        return false;
    }

    @Override
    public List<Item> findItemsByQuery(String query) {
        return itemDao.findItemsByQuery(query);
    }

    @Override
    public List<Item> findItemsByCategoryAndGroup(String category, String group) {
        return itemDao.findItemsByCategoryAndGroup(category, group);
    }

    @Override
    public List<Item> findItemsByCategoryAndGroupAndQuery(String category, String group, String query) {
        return itemDao.findItemsByCategoryAndGroupAndQuery(category, group, query);
    }

    @Override
    public List<Item> getPagedItems(List<Item> items, Integer page, Integer itemsOnPage) {
        int pageAmount = (items.size() + itemsOnPage - 1) / itemsOnPage;
        if(page>pageAmount) {
            return Collections.emptyList();
        }
        int toIndex = (itemsOnPage * (page-1)) + itemsOnPage;
        toIndex = toIndex < items.size() ? toIndex : items.size() ;
        items = items.subList(itemsOnPage * (page-1), toIndex);
        return items;
    }


}
