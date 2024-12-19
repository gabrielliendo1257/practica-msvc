package com.guille.springcloud.microservicio.item.model;

import com.guille.springcloud.microservicio.item.model.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;
    private Integer quantity;

    public Double getTotal() {
        return this.quantity * this.product.getPrice();
    }
}
