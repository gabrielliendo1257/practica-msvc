package com.guille.springcloud.microservicio.item.controller;

import com.guille.springcloud.microservicio.item.model.Item;
import com.guille.springcloud.microservicio.item.service.ItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ItemController
 */
@RestController
@RequestMapping(value = "/api/v1/item")
public class ItemController {

  @Autowired
  @Qualifier(value = "service_web_client")
  private ItemService itemService;

  @GetMapping(value = "/all")
  public ResponseEntity<?> getAllItems() {
    return new ResponseEntity<List<Item>>(this.itemService.findAllItems(),
                                          HttpStatus.OK);
  }

  public ResponseEntity<?> getItemById(@PathVariable Integer id) {
    var optionalItem = this.itemService.findById(id);

    if (!optionalItem.isEmpty()) {
      return new ResponseEntity<Item>(optionalItem.get(), HttpStatus.OK);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }
}
