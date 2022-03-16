package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.ProductDTO;

public interface ProductService {

    Long register(ProductDTO productDTO);

    ProductDTO get(Long id);
}
