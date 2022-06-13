package com.github.ismail2ov.ecommerce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(properties = {
    "spring.test.database.replace=NONE",
    "spring.datasource.url=jdbc:tc:postgresql:14:///test"
})
class BasketRepositoryTest {

  @Autowired
  BasketRepository basketRepository;

  @Test
  @Sql("/scripts/INIT_BASKETS.sql")
  void return_basket_by_user_id() {
    Product product1 = new Product(1L, "Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3", "999,00 €");
    Product product2 = new Product(2L, "Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");
    Items items = new Items(List.of(product1, product2));
    Basket expected = new Basket(2L, 456L, items);

    Optional<Basket> actual = basketRepository.getByUserId(456L);

    assertThat(actual).contains(expected);
  }
}
