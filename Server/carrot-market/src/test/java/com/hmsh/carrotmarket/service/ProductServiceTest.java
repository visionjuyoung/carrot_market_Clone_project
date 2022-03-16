package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void register() {
        ProductDTO productDTO = ProductDTO.builder()
                .title("당금 팝니다.")
                .content("주황 당근 당근")
                .address("수지구")
                .price(10000)
                .memberDTO(MemberDTO.builder().phoneNumber("01093010512").build())
                .build();

        Long register = productService.register(productDTO);
    }

}