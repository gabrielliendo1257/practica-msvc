package com.guille.springcloud.microservicio.item.service.impl;

import com.guille.springcloud.microservicio.item.client.ProductFeignClient;
import com.guille.springcloud.microservicio.item.model.Item;
import com.guille.springcloud.microservicio.item.service.ItemService;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "service_fei")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ProductFeignClient client;

    @Override
    public Optional<Item> findById(Integer id) {
        return Optional.of(new Item(this.client.getRequestProductById(id), 7));
    }

    @Override
    public List<Item> findAllItems() {
        return this.client.getFullProducts()
                .stream()
                .map(product -> {
                    Random random = new Random();
                    return new Item(product, random.nextInt(10) + 1);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findByName(String product) {
        return Optional.of(
                new Item(this.client.getRequestProdutByName(product), 3));
    }
}
