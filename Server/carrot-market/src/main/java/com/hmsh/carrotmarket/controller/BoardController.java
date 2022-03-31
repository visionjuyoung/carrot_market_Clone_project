package com.hmsh.carrotmarket.controller;

import com.hmsh.carrotmarket.CResponseEntity;
import com.hmsh.carrotmarket.dto.BoardDTO;
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

    @PostMapping("")
    public CResponseEntity<Long> registerBoard(BoardDTO boardDTO, MultipartFile[] files) {
        log.info("게시글 등록 boardDTO = {}", boardDTO);
        Long id = boardService.register(boardDTO, files);
        return new CResponseEntity<>(true, StatusCode.OK, id);
    }

    @GetMapping("/{id}")
    public CResponseEntity<BoardDTO> getBoard(@PathVariable Long id) {
        BoardDTO boardDTO = boardService.get(id);
        return new CResponseEntity<>(true, StatusCode.OK, boardDTO);
    }

    @PatchMapping("")
    public CResponseEntity<Object> modifyBoard() {
        return null;
    }
}
