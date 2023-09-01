package spring_lesson_one.product_category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import spring_lesson_one.exception.HandlerException;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductRepository {
  private final List<Product> product;

  public List<Product> getAllProducts() {
    return product;
  }

  public Product getIdProduct(int id) {
    return product.stream()
      .filter(product -> id == product.getId())
      .findFirst()
      .orElseThrow(() -> new HandlerException("Такого id не существует"));
  }
}
