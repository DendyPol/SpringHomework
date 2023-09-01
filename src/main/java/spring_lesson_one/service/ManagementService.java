package spring_lesson_one.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import spring_lesson_one.product_category.Product;

import java.util.List;

@Component
@AllArgsConstructor
public class ManagementService implements Service {
  private final ManagementService service;

  public List<Product> getAllProducts() {
    return service.getAllProducts();
  }

  public Product getIdProduct(int id) {
    return service.getIdProduct(id);
  }

  public Product addProduct(int id) {
    return service.addProduct(id);
  }

  public Product delProduct(int id) {
    return service.delProduct(id);
  }
}
