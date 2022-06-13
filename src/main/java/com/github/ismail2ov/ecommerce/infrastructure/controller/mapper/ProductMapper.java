package com.github.ismail2ov.ecommerce.infrastructure.controller.mapper;

import com.github.ismail2ov.ecommerce.domain.Product;
import com.github.ismail2ov.ecommerce.domain.ProductPageDTO;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.ProductPageRDTO;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.ProductRDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {

  List<ProductRDTO> productRDTOFrom(List<Product> products);

  ProductPageRDTO productPageRDTOFrom(ProductPageDTO productPageDTO);

  Product productFrom(ProductRDTO productRDTO);
}
