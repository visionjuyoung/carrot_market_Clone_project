package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
