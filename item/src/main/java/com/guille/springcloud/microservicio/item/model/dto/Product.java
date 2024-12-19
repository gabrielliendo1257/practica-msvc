package com.guille.springcloud.microservicio.item.model.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private Double price;
    private LocalDate createdAt;
}
