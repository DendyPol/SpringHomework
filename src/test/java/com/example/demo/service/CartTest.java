package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.service.Cart;
import spring_lesson_one.com.example.demo.service.ProductService;

import java.math.BigDecimal;

@SpringBootTest(classes = DemoApplication.class)
public class CartTest {
  @Autowired
  Cart cart;
  @Autowired
  ProductService productService;

  @BeforeEach
  public void setup() {
    cart.getBasket().clear();
  }

  @Test
  public void addProduct() {
    long productId = 1;
    int quantity = 1;
    var product = new Product(productId, "Product one", BigDecimal.valueOf(10));
    productService.create(product);
    var addedProduct = cart.addProduct(productId, quantity);
    Assertions.assertEquals(product, addedProduct);
    Assertions.assertEquals(quantity, cart.getBasket().get(product));
  }

  @Test
  public void delProduct() {
    long productId = 1;
    int quantity = 1;
    var product = new Product(productId, "Product one", BigDecimal.valueOf(10));
    productService.create(product);
    cart.delProduct(productId, quantity);
    Assertions.assertFalse(cart.getBasket().containsKey(product));
  }

  @Test
  public void delProductWithGreeterQuantity() {
    long productId = 1;
    int quantity = 2;
    int greeterQuantity = 3;
    var product = new Product(productId, "Product one", BigDecimal.valueOf(100));
    productService.create(product);
    cart.addProduct(productId, quantity);
    Assertions.assertThrows(ObjectNotFoundException.class, () -> cart.delProduct(quantity, greeterQuantity));
  }

  @Test
  public void addProductNotFound() {
    long productId = 1;
    int quantity = 1;
    Assertions.assertThrows(ObjectNotFoundException.class, () -> cart.addProduct(productId, quantity));
  }
}
