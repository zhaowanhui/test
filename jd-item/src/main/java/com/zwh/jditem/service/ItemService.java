package com.zwh.jditem.service;

import com.zwh.jditem.pojo.Item;

import java.util.List;

public interface ItemService {
    /**
     * 保存商品
     * @param item
     */
    void save(Item item);

    /**
     * 根据条件查询商品
     * @param item
     * @return
     */
    List<Item> findAll(Item item);
}
