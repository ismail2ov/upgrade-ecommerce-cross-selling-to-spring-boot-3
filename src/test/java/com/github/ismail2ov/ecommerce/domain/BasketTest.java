package com.github.ismail2ov.ecommerce.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

@JsonTest
class BasketTest {

  @Autowired
  private JacksonTester<Basket> json;

  @Test
  void serialize_with_items() throws IOException {
    Product product1 = new Product(1L, "Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    Product product2 = new Product(2L, "Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");
    Items items = new Items(List.of(product1, product2));
    Basket basket = new Basket(1L, 123L, items);

    JsonContent<Basket> result = json.write(basket);

    assertThat(result).extractingJsonPathNumberValue("@.id").isEqualTo(1);
    assertThat(result).extractingJsonPathNumberValue("@.userId").isEqualTo(123);
    assertThat(result).extractingJsonPathArrayValue("@.items.products").hasSize(2);
    assertThat(result).extractingJsonPathNumberValue("@.items.products[0].id").isEqualTo(1);
    assertThat(result).extractingJsonPathStringValue("@.items.products[0].name")
        .isEqualTo("Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3");
    assertThat(result).extractingJsonPathStringValue("@.items.products[0].price").isEqualTo("999,00 €");
  }
}
