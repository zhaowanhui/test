package com.zwh.jditem.service.impl;

import com.zwh.jditem.dao.ItemDAO;
import com.zwh.jditem.pojo.Item;
import com.zwh.jditem.service.ItemService;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ItemDAO itemDAO;
    @Override
    @Transactional
    public void save(Item item) {
        this.itemDAO.save(item);
    }

    @Override
    public List<Item> findAll(Item item) {
        //声明查询条件
        Example<Item> example = Example.of(item);
        //根据查询条件进行查询数据
        List<Item> list = this.itemDAO.findAll(example);
        return list;
    }
}
