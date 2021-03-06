package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.LikesDTO;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.dto.PageRequestDTO;
import com.hmsh.carrotmarket.dto.ProductDTO;
import com.hmsh.carrotmarket.dto.ProductListDTO;
import com.hmsh.carrotmarket.enumeration.TradeStatus;
import com.hmsh.carrotmarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
        return new CResponseEntity<>(true, StatusCode.OK, returnId);
    }

    /**
     * ID에 해당하는 상품 정보 조회
     * @param id 상품의 ID
     * @param phoneNumber 로그인 회원의 전화번호
     * @return 상품 정보
     */
    @GetMapping("/{id}")
    public CResponseEntity<ProductDTO> get(@PathVariable Long id, @RequestParam String phoneNumber) {
        log.info("상품 조회 id = {}", id);
        ProductDTO productDTO = productService.get(id, phoneNumber);

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
    public CResponseEntity<List<ProductListDTO>> getList(@RequestParam(required = false) String address,
                                                         @RequestParam(required = false) String keyword,
                                                         PageRequestDTO pageRequestDTO) {
        log.info("상품 리스트 조회 address = {}, keyword = {}", address, keyword);
        List<ProductListDTO> list = productService.getList(pageRequestDTO, address, keyword);
        return new CResponseEntity<>(true, StatusCode.OK, list);
    }

    /**
     * 상품 수정
     * @param productDTO 수정된 상품 정보
     * @param files 수정된 이미지
     * @return 요청 결과
     */
    @PatchMapping("")
    public CResponseEntity<Object> modify(ProductDTO productDTO, MultipartFile[] files) {
        log.info("상품 수정 productDTO = {}", productDTO);
        Boolean result = productService.modify(productDTO, files);

        if (result) return new CResponseEntity<>(true, StatusCode.OK, null);
        return new CResponseEntity<>(false, StatusCode.NOT_FOUND, null);
    }


    /**
     * 상품 삭제
     * @param id 삭제할 상품의 ID
     * @return 요청 결과
     */
    @DeleteMapping("/{id}")
    public CResponseEntity<Boolean> delete(@PathVariable Long id) {
        log.info("상품 삭제 id = {}", id);
        Boolean result = productService.delete(id);

        if (!result) return new CResponseEntity<>(false, StatusCode.NOT_FOUND, null);

        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

    /**
     * 나의 판매목록 조회
     * @param phoneNumber 조회할 회원 전화번호
     * @return 나의 판매 상품 리스트
     */
    @GetMapping("/{phoneNumber}/list")
    public CResponseEntity<List<ProductListDTO>> getMyProducts(@PathVariable String phoneNumber) {
        log.info("나의 판매목록 조회 phoneNumber = {}", phoneNumber);
        List<ProductListDTO> myProductList = productService.getMyProducts(phoneNumber);
        return new CResponseEntity<>(true, StatusCode.OK, myProductList);
    }

    /**
     * 상품의 거래상태 변경
     * @param productId 상품 ID
     * @param tradeStatus 거래 상태
     * @return 작업 처리 결과
     */
    @PatchMapping("/{productId}/status/{tradeStatus}")
    public CResponseEntity<Object> changeTradeStatus(@PathVariable Long productId,
                                                     @PathVariable TradeStatus tradeStatus) {
        log.info("상품 상태 변경 productId = {}, tradeStatus = {}", productId, tradeStatus);
        productService.changeTradeStatus(productId, tradeStatus);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

    /**
     * 좋아요 상품 조회
     * @param phoneNumber 회원 전화번호
     * @return 좋아요 상품 리스트
     */
    @GetMapping("/likes/{phoneNumber}")
    public CResponseEntity<List<ProductListDTO>> getLikesProducts(@PathVariable String phoneNumber) {
        log.info("좋아요 상품 리스트 phoneNumber = {}", phoneNumber);
        List<ProductListDTO> likesList = productService.getLikesList(phoneNumber);
        return new CResponseEntity<>(true, StatusCode.OK, likesList);
    }

    /**
     * 좋아요 등록
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     * @return 좋아요 등록 ID
     */
    @PostMapping("/likes")
    public CResponseEntity<Long> register(@RequestBody LikesDTO likesDTO) {
        log.info("좋아요 등록 likesDTO = {}", likesDTO);
        Long savedId = productService.registerLikes(likesDTO);
        return new CResponseEntity<>(true, StatusCode.OK, savedId);
    }

    /**
     * 좋아요 삭제
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     * @return responseEntity
     */
    @DeleteMapping("/likes")
    public CResponseEntity<Object> remove(@RequestBody LikesDTO likesDTO) {
        log.info("좋아요 삭제 likesDTO = {}", likesDTO);
        productService.removeLikes(likesDTO);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }
}
