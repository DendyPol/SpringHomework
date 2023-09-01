package spring_lesson_one.product_category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {
  private int id;
  private String name;
  private double price;
}
