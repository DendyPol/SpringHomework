package spring_lesson_one.com.example.demo.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Product {
  @Id
  private Long id;
  private String name;
  private BigDecimal price;
}
