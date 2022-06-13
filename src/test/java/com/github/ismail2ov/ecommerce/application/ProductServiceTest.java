package com.github.ismail2ov.ecommerce.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.mockito.Mockito.when;

import com.github.ismail2ov.ecommerce.domain.Product;
import com.github.ismail2ov.ecommerce.domain.ProductNotFoundException;
import com.github.ismail2ov.ecommerce.domain.ProductPageDTO;
import com.github.ismail2ov.ecommerce.domain.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

  public static final Product PRODUCT_1 = new Product("Dell Latitude 3301 Intel Core i7-8565U/8GB/512GB SSD/13.3",
      "999,00 €");
  public static final Product PRODUCT_2 = new Product("Samsonite Airglow Laptop Sleeve 13.3", "41,34 €");
  public static final long PRODUCT_ID = 1002;
  @Mock
  ProductRepository productRepository;

  @InjectMocks
  ProductService productService;

  @Test
  void return_all_products() {
    List<Product> expectedProductsList = List.of(PRODUCT_1, PRODUCT_2);
    when(productRepository.findAll()).thenReturn(expectedProductsList);

    List<Product> actualProductsList = productService.getAllProducts();

    assertThat(actualProductsList).isEqualTo(expectedProductsList);

  }

  @Test
  void return_product_by_id() {
    ProductPageDTO expected = new ProductPageDTO(PRODUCT_1, List.of(PRODUCT_2));
    when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(PRODUCT_1));
    when(productRepository.getCrossSellProducts(PRODUCT_ID)).thenReturn(List.of(PRODUCT_2));

    ProductPageDTO actual = productService.getProductBy(PRODUCT_ID);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  void throw_exception_when_product_does_not_exists() {
    when(productRepository.findById(PRODUCT_ID)).thenThrow(ProductNotFoundException.class);

    Throwable thrown = catchThrowable(() -> productService.getProductBy(PRODUCT_ID));

    AssertionsForClassTypes.assertThat(thrown).isInstanceOf(ProductNotFoundException.class);
  }
}
