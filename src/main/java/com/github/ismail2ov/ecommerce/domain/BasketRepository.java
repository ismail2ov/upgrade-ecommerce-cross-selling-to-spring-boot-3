package com.github.ismail2ov.ecommerce.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

  Optional<Basket> getByUserId(Long userId);

}
