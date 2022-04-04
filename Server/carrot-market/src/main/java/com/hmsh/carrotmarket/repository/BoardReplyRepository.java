package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.BoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    List<BoardReply> findAllByBoard(Board board);
}
