package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.ProductConverter;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Long register(ProductDTO productDTO) {
        log.info("register, productDTO = {}", productDTO);
        Product product = ProductConverter.dtoToEntity(productDTO);
        Product save = productRepository.save(product);
        return save.getId();
    }

    @Override
    public ProductDTO get(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.map(ProductConverter::entityToDTO).orElse(null);
    }
}
