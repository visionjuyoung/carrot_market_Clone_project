package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findProductImagesByProduct(Product product);
}
