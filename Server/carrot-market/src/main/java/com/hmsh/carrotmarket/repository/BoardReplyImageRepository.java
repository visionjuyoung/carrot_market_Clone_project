package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.entity.BoardReplyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReplyImageRepository extends JpaRepository<BoardReplyImage, Long> {

    List<BoardReplyImage> findAllByBoardReply(BoardReply boardReply);
}
