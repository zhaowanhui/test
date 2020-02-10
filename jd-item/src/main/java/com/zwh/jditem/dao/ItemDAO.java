package com.zwh.jditem.dao;

import com.zwh.jditem.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDAO extends JpaRepository<Item,Long> {
}
