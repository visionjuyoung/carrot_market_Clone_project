package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.*;
import com.hmsh.carrotmarket.enumeration.StatusCode;
import com.hmsh.carrotmarket.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
     * @param boardReplyDTO 댓글 정보
     * @return 등록된 댓글의 ID
     */
    @PostMapping("/reply")
    public CResponseEntity<Long> registerBoardReply(BoardReplyDTO boardReplyDTO, MultipartFile[] file) {
        log.info("게시글 댓글 등록 boardReplyDTO = {}", boardReplyDTO);
        Long id = boardService.registerReply(boardReplyDTO, file);
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
        log.info("게시글 댓글 조회 reply = {}", reply);
        return new CResponseEntity<>(true, StatusCode.OK, reply);
    }

    /**
     * 동네생활 게시글 댓글 리스트 조회
     * @param id 게시글 ID
     * @return 게시글의 댓글 리스트
     */
    @GetMapping("/{id}/replies")
    public CResponseEntity<List<BoardReplyListDTO>> getBoardReplies(@PathVariable Long id) {
        List<BoardReplyListDTO> replyList = boardService.getReplyList(id);
        return new CResponseEntity<>(true, StatusCode.OK, replyList);
    }

    /**
     * 동네생활 게시글 댓글 수정
     * @param boardReplyDTO 수정한 댓글 정보
     * @return null
     */
    @PatchMapping("/reply")
    public CResponseEntity<Object> modifyBoardReply(@RequestBody BoardReplyDTO boardReplyDTO) {
        log.info("게시글 댓글 수정 boardReplyDTO = {}", boardReplyDTO);
        boardService.modifyReply(boardReplyDTO);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }

    /**
     * 동네생활 게시글 삭제
     * @param id 삭제할 댓글의 ID
     * @return null
     */
    @DeleteMapping("/reply/{id}")
    public CResponseEntity<Object> deleteBoardReply(@PathVariable Long id) {
        log.info("게시글 댓글 삭제 id = {}", id);
        boardService.deleteReply(id);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }


    /**
     * 좋아요 누른 댓글 상품 조회
     * @param phoneNumber 회원 전화번호
     * @return 좋아요 누른 댓글 리스트
     */
    @GetMapping("/reply/likes/{phoneNumber}")
    public CResponseEntity<List<BoardReplyDTO>> getLikesProducts(@PathVariable String phoneNumber) {
        log.info("좋아요 상품 리스트 phoneNumber = {}", phoneNumber);
        List<BoardReplyDTO> likesList = boardService.getLikesReplyList(phoneNumber);
        return new CResponseEntity<>(true, StatusCode.OK, likesList);
    }

    /**
     * 댓글 좋아요 등록
     * @param likesDTO 좋아요 등록 멤버와 댓글 정보
     * @return 좋아요 등록 댓글 ID
     */
    @PostMapping("/reply/likes")
    public CResponseEntity clickLike(@RequestBody LikesDTO likesDTO) {
        long likes = boardService.registReplyLike(likesDTO);

        log.info("댓글 좋아요 수 = {}", likes);
        return new CResponseEntity<>(true, StatusCode.OK, likes);
    }

    /**
     * 댓글 좋아요 삭제
     * @param likesDTO 좋아요 등록 멤버와 댓글 정보
     * @return responseEntity
     */
    @DeleteMapping("/reply/likes")
    public CResponseEntity<Object> remove(@RequestBody LikesDTO likesDTO) {
        log.info("좋아요 삭제 likesDTO = {}", likesDTO);
        boardService.removeReplyLikes(likesDTO);
        return new CResponseEntity<>(true, StatusCode.OK, null);
    }
}
