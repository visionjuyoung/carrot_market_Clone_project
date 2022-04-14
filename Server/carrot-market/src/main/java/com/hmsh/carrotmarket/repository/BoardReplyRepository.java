package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Board;
import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardReplyRepository extends JpaRepository<BoardReply, Long> {

    List<BoardReply> findAllByBoard(Board board);

    void deleteAllByBoard(Board board);

    @Query("select p.id, p.content, p.board_id, p.modDate, p.likes" +
            "from BoardReply p " +
            "left outer join Likes l on p = l.boardReply " +
            "where l.member = :member " +
            "group by p")
    List<Object[]> getBoardByLikes(Member member);
}
