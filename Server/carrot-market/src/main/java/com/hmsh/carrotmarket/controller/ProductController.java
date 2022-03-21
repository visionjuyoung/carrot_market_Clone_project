package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.StatusCode;
import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/register")
    public CResponseEntity<String> register(ProductDTO productDTO, MultipartFile[] files) {
        Long returnId = productService.register(productDTO, files);
        boolean isSuccess = returnId != null;
        StatusCode code = returnId != null ? StatusCode.OK : StatusCode.INTERNAL_SERVER_ERROR;
        return new CResponseEntity<>(isSuccess, code, code.getMessage(), null);
    }

    @GetMapping("/{id}")
    public CResponseEntity<ProductDTO> get(@PathVariable Long id) {
        ProductDTO productDTO = productService.get(id);
        return new CResponseEntity<>(true, StatusCode.OK, productDTO);
    }

    @GetMapping("/list")
    public CResponseEntity<List<ProductListDTO>> getList(@RequestParam String address, PageRequestDTO pageRequestDTO) {
        List<ProductListDTO> list = productService.getList(pageRequestDTO, address);
        return new CResponseEntity<>(true, StatusCode.OK, list);
    }

}
