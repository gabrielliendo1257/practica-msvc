package com.guille.springcloud.microservicio.product.model.listener;

import java.time.LocalDate;

import com.guille.springcloud.microservicio.product.model.Product;

import jakarta.persistence.PrePersist;

public class ProductEventListener {

    @PrePersist
    public void createdAt(Product product) {
        product.setCreatedAt(LocalDate.now());
    }
}
