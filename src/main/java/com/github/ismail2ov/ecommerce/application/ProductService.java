package com.github.ismail2ov.ecommerce.application;

import com.github.ismail2ov.ecommerce.domain.Product;
import com.github.ismail2ov.ecommerce.domain.ProductNotFoundException;
import com.github.ismail2ov.ecommerce.domain.ProductPageDTO;
import com.github.ismail2ov.ecommerce.domain.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  public ProductPageDTO getProductBy(Long id) {
    Product product = this.productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    List<Product> crossSellProducts = productRepository.getCrossSellProducts(id);

    return new ProductPageDTO(product, crossSellProducts);
  }
}
