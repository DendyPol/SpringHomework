package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import spring_lesson_one.com.example.demo.DemoApplication;
import spring_lesson_one.exception.HandlerException;
import spring_lesson_one.product_category.Cart;
import spring_lesson_one.product_category.Product;
import spring_lesson_one.product_category.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TestCart {
  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private Cart cart;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddProduct() {
    int productId = 1;
    int productQuantity = 2;
    double productPrice = 10.1;
    var product = new Product(productId, "Product Name", productPrice);
    when(productRepository.getIdProduct(productId)).thenReturn(product);
    var addedProduct = cart.addProduct(productId, productQuantity);
    assertEquals(product, addedProduct);
    assertEquals(productQuantity, cart.getBasket().get(product));
  }

  @Test
  public void testDelProduct() {
    int productId = 1;
    int productQuantity = 3;
    int productDelete = 2;
    double productPrice = 10.1;
    var product = new Product(productId, "Product Name", productPrice);
    cart.getBasket().put(product, productQuantity);
    when(productRepository.getIdProduct(productId)).thenReturn(product);
    var removedProduct = cart.delProduct(productId, productDelete);
    assertEquals(product, removedProduct);
    assertEquals(productQuantity - productDelete, cart.getBasket().get(product));
  }

  @Test
  public void testDelInvalidId() {
    int invalidId = 99;
    int productQuantity = 1;
    when(productRepository.getIdProduct(invalidId)).thenReturn(null);
    Assertions.assertThrows(HandlerException.class, () -> cart.delProduct(invalidId, productQuantity));
  }

  @Test
  public void testDelProductGreaterQuantity() {
    int productId = 1;
    int productQuantity = 2;
    int productDelete = 3;
    double productPrice = 10.1;
    var product = new Product(productId, "Product Name", productPrice);
    cart.getBasket().put(product, productQuantity);
    when(productRepository.getIdProduct(productId)).thenReturn(product);
    Assertions.assertThrows(HandlerException.class, () -> cart.delProduct(productQuantity, productDelete));
  }

  @Test
  public void testPrintAllProducts() {
    int productId = 1;
    double productPrice = 10.0;
    List<Product> allProducts = new ArrayList<>();
    allProducts.add(new Product(productId, "Product One", productPrice));
    allProducts.add(new Product(productId, "Product Two", productPrice));
    when(productRepository.getAllProducts()).thenReturn(allProducts);
    var actualProducts = cart.printAllProducts();
    Assertions.assertEquals(allProducts, actualProducts);
  }
}
