package com.guille.springcloud.microservicio.item.service;

import com.guille.springcloud.microservicio.item.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<Item> findById(Integer id);

    List<Item> findAllItems();

    Optional<Item> findByName(String name);
}
