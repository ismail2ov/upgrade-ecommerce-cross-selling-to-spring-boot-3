package com.github.ismail2ov.ecommerce.infrastructure.controller;

import com.github.ismail2ov.ecommerce.application.BasketService;
import com.github.ismail2ov.ecommerce.domain.Basket;
import com.github.ismail2ov.ecommerce.domain.Product;
import com.github.ismail2ov.ecommerce.infrastructure.controller.api.UsersApi;
import com.github.ismail2ov.ecommerce.infrastructure.controller.mapper.BasketMapper;
import com.github.ismail2ov.ecommerce.infrastructure.controller.mapper.ProductMapper;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.BasketRDTO;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.ProductRDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketController implements UsersApi {

  private final BasketService basketService;

  private final ProductMapper productMapper;

  private final BasketMapper basketMapper;

  @Override
  public ResponseEntity<BasketRDTO> addProductToBasket(Long userId, ProductRDTO productRDTO) {
    Product product = productMapper.productFrom(productRDTO);
    Basket basket = basketService.addProductToBasket(userId, product);

    return ResponseEntity.ok(basketMapper.basketRDTOFrom(basket));
  }

  @Override
  public ResponseEntity<BasketRDTO> getByUserId(Long userId) {
    Basket basket = basketService.getBy(userId);

    return ResponseEntity.ok(basketMapper.basketRDTOFrom(basket));
  }
}
