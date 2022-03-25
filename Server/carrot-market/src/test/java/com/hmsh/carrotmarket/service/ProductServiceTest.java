package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


}