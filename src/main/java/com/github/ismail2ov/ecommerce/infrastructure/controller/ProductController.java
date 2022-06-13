package com.github.ismail2ov.ecommerce.infrastructure.controller;

import com.github.ismail2ov.ecommerce.application.ProductService;
import com.github.ismail2ov.ecommerce.domain.Product;
import com.github.ismail2ov.ecommerce.domain.ProductNotFoundException;
import com.github.ismail2ov.ecommerce.domain.ProductPageDTO;
import com.github.ismail2ov.ecommerce.infrastructure.controller.api.ProductsApi;
import com.github.ismail2ov.ecommerce.infrastructure.controller.mapper.ProductMapper;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.ProductPageRDTO;
import com.github.ismail2ov.ecommerce.infrastructure.controller.model.ProductRDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductsApi {

  private final ProductService productService;

  private final ProductMapper productMapper;

  @Override
  public ResponseEntity<List<ProductRDTO>> getAll() {
    List<Product> products = productService.getAllProducts();

    return ResponseEntity.ok(productMapper.productRDTOFrom(products));
  }

  @Override
  public ResponseEntity<ProductPageRDTO> getById(Long id) {
    try {
      ProductPageDTO productPageDTO = productService.getProductBy(id);

      return ResponseEntity.ok(productMapper.productPageRDTOFrom(productPageDTO));

    } catch (ProductNotFoundException e) {
      log.info(e.getMessage());
      return ResponseEntity.notFound().build();
    }
  }
}
