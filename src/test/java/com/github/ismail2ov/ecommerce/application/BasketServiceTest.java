package com.github.ismail2ov.ecommerce.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

import com.github.ismail2ov.ecommerce.domain.Basket;
import com.github.ismail2ov.ecommerce.domain.BasketNotFoundException;
import com.github.ismail2ov.ecommerce.domain.BasketRepository;
import com.github.ismail2ov.ecommerce.domain.Items;
import com.github.ismail2ov.ecommerce.domain.Product;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class BasketServiceTest {

  public static final long USER_ID = 101;
  public static final Product PRODUCT = new Product(3L, "Logitech Wireless Mouse M185", "10,78 €");
  public static final Basket EXPECTED_BASKET = new Basket(1L, USER_ID, new Items(List.of(PRODUCT)));

  @Mock
  BasketRepository basketRepository;

  @InjectMocks
  BasketService basketService;

  @Test
  void return_basket_by_id() {
    when(basketRepository.getByUserId(USER_ID)).thenReturn(Optional.of(EXPECTED_BASKET));

    Basket actualBasket = basketService.getBy(USER_ID);

    assertThat(actualBasket).isEqualTo(EXPECTED_BASKET);
  }

  @Test
  void throw_exception_when_basket_does_not_exists() {
    when(basketRepository.getByUserId(USER_ID)).thenThrow(BasketNotFoundException.class);

    Throwable thrown = catchThrowable(() -> basketService.getBy(USER_ID));

    AssertionsForClassTypes.assertThat(thrown).isInstanceOf(BasketNotFoundException.class);
  }

  @Test
  void return_basket_if_exists() {
    when(basketRepository.getByUserId(USER_ID)).thenReturn(Optional.of(EXPECTED_BASKET));
    when(basketRepository.save(EXPECTED_BASKET)).thenReturn(EXPECTED_BASKET);

    Basket actualBasket = basketService.addProductToBasket(USER_ID, PRODUCT);

    assertThat(actualBasket).isEqualTo(EXPECTED_BASKET);
  }

  @Test
  void create_basket_if_not_exists() {
    Basket expectedBasket = new Basket(null, USER_ID, new Items(List.of(PRODUCT)));
    when(basketRepository.getByUserId(USER_ID)).thenReturn(Optional.empty());
    when(basketRepository.save(expectedBasket)).thenReturn(expectedBasket);

    Basket actualBasket = basketService.addProductToBasket(USER_ID, PRODUCT);

    assertThat(actualBasket).isEqualTo(expectedBasket);
  }
}
