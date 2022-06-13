package com.github.ismail2ov.ecommerce.integration;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.ismail2ov.ecommerce.domain.Basket;
import com.github.ismail2ov.ecommerce.domain.BasketRepository;
import com.github.ismail2ov.ecommerce.domain.Items;
import com.github.ismail2ov.ecommerce.domain.Product;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasketIntegrationTest extends BaseTestcontainers {

  public static final int USER_ID = 2;
  public static final String BASKET_URL = "/users/" + USER_ID + "/basket";
  @Autowired
  public TestRestTemplate testRestTemplate;
  @Autowired
  BasketRepository basketRepository;

  @BeforeEach
  void setUp() {
    Product product = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");
    Basket basket = new Basket(1L, (long) USER_ID, new Items(List.of(product)));
    basketRepository.save(basket);
  }

  @AfterEach
  void tearDown() {
    basketRepository.deleteAll();
  }

  @Test
  void when_add_product_to_empty_basket_then_basket_has_1_item() {
    int userId = 1;
    Product productToAdd = new Product(4L, "Fellowes Mouse Pad Black", "1,34 €");

    ResponseEntity<Basket> result = testRestTemplate.postForEntity("/users/" + userId + "/basket", productToAdd,
        Basket.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(Objects.requireNonNull(result.getBody()).getItems().getProducts()).hasSize(1);
  }

  @Test
  void when_add_product_to_basket_with_same_item_then_basket_has_1_item() {

    Product productToAdd = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");

    ResponseEntity<Basket> result = testRestTemplate.postForEntity(BASKET_URL, productToAdd, Basket.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(Objects.requireNonNull(result.getBody()).getItems().getProducts()).hasSize(1);
  }

  @Test
  void when_add_product_to_basket_with_item_then_basket_has_2_items() {

    Product productToAdd = new Product(4L, "Fellowes Mouse Pad Black", "1,34 €");

    ResponseEntity<Basket> result = testRestTemplate.postForEntity(BASKET_URL, productToAdd, Basket.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(Objects.requireNonNull(result.getBody()).getItems().getProducts()).hasSize(2);
  }

  @Test
  void when_user_has_not_basket_then_returns_not_found() {
    int userId = 1;

    ResponseEntity<Basket> result = testRestTemplate.getForEntity("/users/" + userId + "/basket", Basket.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  void when_user_has_basket_then_returns_the_basket() {

    ResponseEntity<Basket> result = testRestTemplate.getForEntity(BASKET_URL, Basket.class);

    assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(result).isNotNull();
    assertThat(Objects.requireNonNull(result.getBody()).getItems().getProducts()).hasSize(1);
  }
}
