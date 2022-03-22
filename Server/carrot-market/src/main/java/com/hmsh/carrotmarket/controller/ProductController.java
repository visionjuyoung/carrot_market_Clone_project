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
    @PostMapping("")
    public CResponseEntity<Long> register(ProductDTO productDTO, MultipartFile[] files) {
        log.info("상품 등록 productDTO = {}", productDTO);
        Long returnId = productService.register(productDTO, files);
        boolean isSuccess = returnId != null;
        StatusCode code = returnId != null ? StatusCode.OK : StatusCode.INTERNAL_SERVER_ERROR;
        return new CResponseEntity<>(isSuccess, code, returnId);
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
            return new CResponseEntity<>(false, StatusCode.NOT_FOUND, null);
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

    /**
     * 상품 수정
     * @param productDTO 수정된 상품 정보
     * @param files 수정된 이미지
     * @return 처리 결과
     */
    @PutMapping("")
    public CResponseEntity<Object> modify(ProductDTO productDTO, MultipartFile[] files) {
        log.info("상품 수정 productDTO = {}", productDTO);
        Boolean result = productService.modify(productDTO, files);

        if (result) return new CResponseEntity<>(true, StatusCode.OK, null);
        return new CResponseEntity<>(false, StatusCode.NOT_FOUND, null);
    }


    @DeleteMapping("/{id}")
    public CResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("상품 삭제 id = {}", id);
        Boolean result = productService.delete(id);

        if (!result) return new CResponseEntity<>(false, StatusCode.NOT_FOUND, null);

        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

}
