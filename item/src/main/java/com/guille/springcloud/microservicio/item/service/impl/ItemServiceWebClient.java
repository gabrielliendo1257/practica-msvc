package com.guille.springcloud.microservicio.item.service.impl;

import com.guille.springcloud.microservicio.item.model.Item;
import com.guille.springcloud.microservicio.item.model.dto.Product;
import com.guille.springcloud.microservicio.item.service.ItemService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * ItemServiceWebClient
 */
@Service(value = "service_web_client")
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder client;

    public ItemServiceWebClient(WebClient.Builder client) {
        this.client = client;
    }

    @Override
    public Optional<Item> findById(Integer id) {
        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);
        return Optional.ofNullable(
                this.client.build()
                        .get()
                        .uri("/{id}", params)
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Product.class)
                        .map(product -> new Item(product, new Random().nextInt(10) * 1))
                        .block());
    }

    @Override
    public List<Item> findAllItems() {
        return this.client.build()
                .get()
                .uri("/all")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Product.class)
                .map(product -> new Item(product, new Random().nextInt(10) + 1))
                .collectList()
                .block();
    }

    @Override
    public Optional<Item> findByName(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("product", name);
        return Optional.ofNullable(
                this.client.build()
                        .get()
                        .uri("?product={name}")
                        .accept(MediaType.APPLICATION_JSON)
                        .retrieve()
                        .bodyToMono(Item.class)
                        .block());
    }
}
