package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    List<BoardReply> findAllByBoard(Board board);

    void deleteAllByBoard(Board board);

    @Query(value = "select p.id, p.content, p.boardId, p.modDate, p.likes " +
            "from BoardReply p " +
            "left outer join ReplyLikes l on p = l.boardReply " +
            "where l.member = :member " +
            "group by p", nativeQuery = true)
    List<Object[]> getBoardReplyByReplyLikes(@Param("member") Member member);
}
