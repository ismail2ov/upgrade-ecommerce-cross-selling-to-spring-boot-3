package com.github.ismail2ov.ecommerce.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query(value = "SELECT *  FROM products WHERE id IN (SELECT xsell_id FROM cross_sales WHERE product_id = :productId)", nativeQuery = true)
  List<Product> getCrossSellProducts(long productId);

  @Modifying(clearAutomatically = true)
  @Transactional
  @Query(value = "INSERT INTO cross_sales (product_id, xsell_id) VALUES (:productId, :xsellId)", nativeQuery = true)
  void addCrossSellProducts(long productId, long xsellId);
}
