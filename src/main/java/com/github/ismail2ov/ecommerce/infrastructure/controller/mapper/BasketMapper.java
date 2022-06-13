package com.github.ismail2ov.ecommerce.infrastructure.controller.mapper;

import com.github.ismail2ov.ecommerce.domain.Basket;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.BasketRDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BasketMapper {

  BasketRDTO basketRDTOFrom(Basket basket);
}
