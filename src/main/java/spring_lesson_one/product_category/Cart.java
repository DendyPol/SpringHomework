package spring_lesson_one.product_category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_lesson_one.exception.HandlerException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
@AllArgsConstructor
public class Cart {
  private final Map<Product, Integer> basket = new HashMap<>();
  @Autowired
  private ProductRepository productRepository;

  public Product addProduct(int id, int quantity) {
    var product = productRepository.getIdProduct(id);
    basket.put(product, quantity);
    return product;
  }

  public Product delProduct(int id, int quantity) {
    var product = productRepository.getIdProduct(id);
    if (product == null) {
      throw new HandlerException("Такого id нет");
    } else {
      basket.compute(product, (key, value) -> value - quantity);
      return product;
    }
  }

  public List<Product> printAllProducts() {
    return productRepository.getAllProducts();
  }
}
