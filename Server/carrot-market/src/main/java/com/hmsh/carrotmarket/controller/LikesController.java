package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.LikesDTO;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesController {

    private final LikesService likesService;


    /**
     * 좋아요 등록
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     * @return 좋아요 등록 ID
     */
    @PostMapping("")
    public CResponseEntity<Long> register(@RequestBody LikesDTO likesDTO) {
        log.info("좋아요 등록 likesDTO = {}", likesDTO);
        try {
            Long savedId = likesService.register(likesDTO);
            return new CResponseEntity<>(true, StatusCode.OK, savedId);
        } catch (Exception e) {
            log.error("좋아요 등록 실패", e);
            return new CResponseEntity<>(false, StatusCode.INTERNAL_SERVER_ERROR, null);
        }
    }

    /**
     * 좋아요 삭제
     * @param likesDTO 좋아요 등록 멤버와 상품 정보
     * @return responseEntity
     */
    @DeleteMapping("")
    public CResponseEntity<Object> remove(@RequestBody LikesDTO likesDTO) {
        log.info("좋아요 삭제 likesDTO = {}", likesDTO);
        try {
            likesService.remove(likesDTO);
            return new CResponseEntity<>(true, StatusCode.OK, null);
        } catch (Exception e) {
            log.error("좋아요 삭제 실패", e);
            return new CResponseEntity<>(false, StatusCode.INTERNAL_SERVER_ERROR, null);
        }
    }
}
