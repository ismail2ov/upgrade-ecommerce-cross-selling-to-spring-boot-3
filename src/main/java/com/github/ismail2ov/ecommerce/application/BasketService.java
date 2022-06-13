package com.github.ismail2ov.ecommerce.application;

import com.github.ismail2ov.ecommerce.domain.Basket;
import com.github.ismail2ov.ecommerce.domain.BasketNotFoundException;
import com.github.ismail2ov.ecommerce.domain.BasketRepository;
import com.github.ismail2ov.ecommerce.domain.Product;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

  private final BasketRepository basketRepository;

  public Basket getBy(Long userId) {
    return basketRepository.getByUserId(userId).orElseThrow(BasketNotFoundException::new);
  }

  public Basket addProductToBasket(Long userId, Product product) {
    Optional<Basket> optBasket = basketRepository.getByUserId(userId);
    Basket basket;
    if (optBasket.isPresent()) {
      basket = optBasket.get();
    } else {
      basket = new Basket();
      basket.setUserId(userId);
    }

    basket.addItem(product);

    return basketRepository.save(basket);
  }
}
