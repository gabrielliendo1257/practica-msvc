package com.guille.springcloud.microservicio.product.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guille.springcloud.microservicio.product.model.Product;
import com.guille.springcloud.microservicio.product.repository.ProductRepository;
import com.guille.springcloud.microservicio.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    final private ProductRepository productRepository;
    final private Environment environment;

    public ProductServiceImpl(ProductRepository productRepository, Environment environment) {
        this.productRepository = productRepository;
        this.environment = environment;
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<Product> findById(Integer id) {
        return this.productRepository.findById(id).map(product -> {
            product.setPort(Integer.parseInt(this.environment.getProperty("local.server.port")));
            return product;
        });
    }

    @Override
    @Transactional(readOnly = false)
    public List<Product> findAllProducts() {
        return ((List<Product>) this.productRepository.findAll()).stream().map(product -> {
            product.setPort(Integer.parseInt(this.environment.getProperty("local.server.port")));
            return product;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false)
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = false)
    public List<Product> findByCreatedAtAfter(LocalDate createdAt) {
        return this.productRepository.findByCreatedAtAfter(createdAt);
    }

    @Override
    @Transactional
    public Product saveProduct(Product product) {
        return this.productRepository.save(product);
    }

}
