package spring_lesson_one.service;

import spring_lesson_one.product_category.Product;

import java.util.List;

public interface Service {

  List<Product> getAllProducts();

  Product getIdProduct(int id);

  Product addProduct(int id);

  Product delProduct(int id);
}
