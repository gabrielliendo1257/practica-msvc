package com.guille.springcloud.microservicio.product.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guille.springcloud.microservicio.product.model.Product;
import com.guille.springcloud.microservicio.product.service.ProductService;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getFullProducts() {
        return new ResponseEntity<List<Product>>(this.productService.findAllProducts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getRequestProductById(@PathVariable Integer id) {
        var optionalProduct = this.productService.findById(id);
        if (!optionalProduct.isEmpty()) {
            return new ResponseEntity<Product>(this.productService.findById(id).get(), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getRquestProdustByName(@RequestParam(name = "product") String product) {
        var optionalProduct = this.productService.findByName(product);
        if (!optionalProduct.isEmpty()) {
            return new ResponseEntity<Product>(optionalProduct.get(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(Collections.singletonMap("message", "No se encontro el Producto con el nombre ".concat(product)));
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<Product>(this.productService.saveProduct(product), HttpStatus.CREATED);
    }

}
