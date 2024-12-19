package com.guille.springcloud.microservicio.product.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guille.springcloud.microservicio.product.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    Optional<Product> findByName(String name);

    List<Product> findByCreatedAtAfter(LocalDate CreatedAt);
}
