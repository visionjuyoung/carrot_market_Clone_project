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
import java.util.Objects;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    /**
     * 중고 상품의 정보와 이미지를 등록
     * @param productDTO 상품 등록 정보
     * @param files 이미지 파일
     * @return 처리 결과
     */
    @PostMapping("/register")
    public CResponseEntity<Long> register(ProductDTO productDTO, MultipartFile[] files) {
        log.info("상품 등록 productDTO = {}", productDTO);
        Long returnId = productService.register(productDTO, files);
        boolean isSuccess = returnId != null;
        StatusCode code = returnId != null ? StatusCode.OK : StatusCode.INTERNAL_SERVER_ERROR;
        return new CResponseEntity<>(isSuccess, code, code.getMessage(), returnId);
    }

    /**
     * ID에 해당하는 상품 정보 조회
     * @param id 상품의 ID
     * @return 상품 정보
     */
    @GetMapping("/{id}")
    public CResponseEntity<ProductDTO> get(@PathVariable Long id) {
        log.info("상품 조회 id = {}", id);
        ProductDTO productDTO = productService.get(id);

        if (Objects.isNull(productDTO)) {
            return new CResponseEntity<>(false, StatusCode.NOT_FOUND,
                    StatusCode.NOT_FOUND.getMessage(), null);
        }

        return new CResponseEntity<>(true, StatusCode.OK, productDTO);
    }

    /**
     * address 에 맞는 상품들을 최근 수정순으로 정렬해 20개씩 조회
     * @param address 주소
     * @param pageRequestDTO 페이지 요청 파라미터
     * @return 상품 리스트
     */
    @GetMapping("/list")
    public CResponseEntity<List<ProductListDTO>> getList(@RequestParam String address, PageRequestDTO pageRequestDTO) {
        log.info("상품 리스트 조회 address = {}", address);
        List<ProductListDTO> list = productService.getList(pageRequestDTO, address);
        return new CResponseEntity<>(true, StatusCode.OK, list);
    }

}
