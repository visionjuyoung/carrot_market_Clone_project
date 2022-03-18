package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;

import java.util.List;

public interface ProductService {

    Long register(ProductDTO productDTO);

    ProductDTO get(Long id);

    List<ProductListDTO> getList(PageRequestDTO pageRequestDTO, String address);
}
