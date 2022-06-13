package com.github.ismail2ov.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {

  List<Product> products = new ArrayList<>();

  public void addItem(Product product) {
    if (!products.contains(product)) {
      products.add(product);
    }
  }
}
