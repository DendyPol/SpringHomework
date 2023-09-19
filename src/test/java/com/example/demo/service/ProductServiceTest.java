package com.example.demo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.com.example.demo.exception.ObjectNotFoundException;
import spring_lesson_one.com.example.demo.jpa.Product;
import spring_lesson_one.com.example.demo.jpa.ProductRepository;
import spring_lesson_one.com.example.demo.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = DemoApplication.class)
public class ProductServiceTest {
  @Autowired
  ProductService productService;

  @BeforeEach
  public void setup() {
    ProductRepository productRepository = new ProductRepository(new ArrayList<>());
    productService = new ProductService(productRepository);
  }

  @Test
  public void findAll() {
    var productOne = new Product(1L, "Product One", new BigDecimal(10));
    var productTwo = new Product(2L, "Product Two", new BigDecimal(11));
    productService.create(productOne);
    productService.create(productTwo);
    var expected = List.of(productOne, productTwo);
    var result = productService.findAll();
    Assertions.assertEquals(expected, result);
  }

  @Test
  public void findById() {
    var product = new Product(1L, "Product one", new BigDecimal(20));
    productService.create(product);
    Assertions.assertEquals(product, productService.findById(product.getId()));
  }

  @Test
  public void create() {
    var product = new Product(1L, "Product one", new BigDecimal(20));
    var result = productService.create(product);
    Assertions.assertEquals(product, result);
  }

  @Test
  public void delete() {
    var productOne = new Product(1L, "Product one", new BigDecimal(20));
    productService.create(productOne);
    productService.delete(productOne.getId());
    Assertions.assertThrows(ObjectNotFoundException.class, () -> {
      productService.findById(productOne.getId());
    });
  }
}
