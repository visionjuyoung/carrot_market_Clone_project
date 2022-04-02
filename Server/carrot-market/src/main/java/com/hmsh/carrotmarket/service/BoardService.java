package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.BoardDTO;
import com.hmsh.carrotmarket.dto.BoardReplyDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {

    // Board
    Long register(BoardDTO boardDTO, MultipartFile[] files);

    BoardDTO get(Long id);

    void modify(BoardDTO boardDTO, MultipartFile[] files);

    void delete(Long id);

    // BoardReply
    Long registerReply(BoardReplyDTO boardReplyRegistrationDTO);

    BoardReplyDTO getReply(Long id);
}
