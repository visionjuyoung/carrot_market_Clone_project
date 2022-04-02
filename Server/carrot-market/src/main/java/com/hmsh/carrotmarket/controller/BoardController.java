package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.BoardDTO;
import com.hmsh.carrotmarket.dto.BoardReplyDTO;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/board")
public class BoardController {

    private final BoardService boardService;


    /**
     * 동네생활 게시글 등록
     * @param boardDTO 게시글 정보
     * @param files 업로드된 이미지 파일들
     * @return 등록된 게시글의 ID
     */
    @PostMapping("")
    public CResponseEntity<Long> registerBoard(BoardDTO boardDTO, MultipartFile[] files) {
        log.info("게시글 등록 boardDTO = {}", boardDTO);
        Long id = boardService.register(boardDTO, files);
        return new CResponseEntity<>(true, StatusCode.OK, id);
    }

    /**
     * 동네생활 게시글 조회
     * @param id 조회할 게시글 ID
     * @return 게시글 정보
     */
    @GetMapping("/{id}")
    public CResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        BoardDTO boardDTO = boardService.get(id);
        log.info("게시글 조회 boardDTO = {}", boardDTO);
        return new CResponseEntity<>(true, StatusCode.OK, boardDTO);
    }

    /**
     * 동네생활 게시글 수정
     * @param boardDTO 수정된 게시글 정보
     * @param files 수정된 이미지 파일들
     * @return null
     */
    @PatchMapping("")
    public CResponseEntity<Object> modifyBoard(BoardDTO boardDTO, MultipartFile[] files) {
        log.info("게시글 수정 boardDTO = {}", boardDTO);
        boardService.modify(boardDTO, files);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

    /**
     * 동네생활 게시글 삭제
     * @param id 삭제할 게시글 ID
     * @return null
     */
    @DeleteMapping("/{id}")
    public CResponseEntity<Object> deleteBoard(@PathVariable Long id) {
        log.info("게시글 삭제 id = {}", id);
        boardService.delete(id);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

    /**
     * 동네생활 게시글 댓글 등록
     * @param replyRegistrationDTO 댓글 정보
     * @return 등록된 댓글의 ID
     */
    @PostMapping("/reply")
    public CResponseEntity<Long> registerBoardReply(@RequestBody BoardReplyDTO replyRegistrationDTO) {
        log.info("게시글 댓글 등록 replyRegDTO = {}", replyRegistrationDTO);
        Long id = boardService.registerReply(replyRegistrationDTO);
        return new CResponseEntity<>(true, StatusCode.OK, id);
    }

    /**
     * 동네생활 게시글 댓글 조회
     * @param id 조회할 댓글의 ID
     * @return 댓글 정보
     */
    @GetMapping("/reply/{id}")
    public CResponseEntity<BoardReplyDTO> getBoardReply(@PathVariable Long id) {
        BoardReplyDTO reply = boardService.getReply(id);
        return new CResponseEntity<>(true, StatusCode.OK, reply);
    }
}
