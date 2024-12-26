package com.guille.springcloud.microservicio.item.client;

import com.guille.springcloud.microservicio.item.model.dto.Product;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "product")
public interface ProductFeignClient {

  @GetMapping(value = "/all") List<Product> getFullProducts();

  @GetMapping(value = "/{id}")
  Product getRequestProductById(@PathVariable Integer id);

  @GetMapping
  Product
  getRequestProdutByName(@RequestParam(name = "product") String product);
}
