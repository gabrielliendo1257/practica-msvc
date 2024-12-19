package com.guille.springcloud.microservicio.product.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.guille.springcloud.microservicio.product.model.Product;

public interface ProductService {

    Optional<Product> findById(Integer id);

    List<Product> findAllProducts();

    Optional<Product> findByName(String name);

    List<Product> findByCreatedAtAfter(LocalDate afterCreatedAt);

    Product saveProduct(Product product);
}
