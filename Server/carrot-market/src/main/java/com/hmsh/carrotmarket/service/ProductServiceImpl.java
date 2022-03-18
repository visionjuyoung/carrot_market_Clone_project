package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.ProductConverter;
import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Transactional
    public ProductDTO get(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setViews(product.getViews() + 1);
            productRepository.save(product);
            return ProductConverter.entityToDTO(product);
        }

        return null;
    }

    @Override
    public List<ProductListDTO> getList(PageRequestDTO pageRequestDTO, String address) {
        return productRepository.getListByAddress(
                pageRequestDTO.getPageable(Sort.by("modDate").descending()), address).stream()
                .map(ProductConverter::entityToListDTO)
                .collect(Collectors.toList());
    }
}
