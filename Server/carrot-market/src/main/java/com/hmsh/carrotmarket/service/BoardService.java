package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.BoardDTO;
import com.hmsh.carrotmarket.dto.BoardReplyDTO;
import com.hmsh.carrotmarket.dto.BoardReplyListDTO;
import com.hmsh.carrotmarket.dto.LikesDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    // Board
    Long register(BoardDTO boardDTO, MultipartFile[] files);

    BoardDTO get(Long id);

    void modify(BoardDTO boardDTO, MultipartFile[] files);

    void delete(Long id);

    // BoardReply
    Long registerReply(BoardReplyDTO boardReplyDTO, MultipartFile[] files);

    BoardReplyDTO getReply(Long id);

    List<BoardReplyListDTO> getReplyList(Long id);

    void modifyReply(BoardReplyDTO boardReplyRegistrationDTO);

    void deleteReply(Long id);

    long registReplyLike(LikesDTO likesDTO);

    List<BoardReplyDTO> getLikesReplyList(String phoneNumber);

    void removeReplyLikes (LikesDTO likesDTO);

}
