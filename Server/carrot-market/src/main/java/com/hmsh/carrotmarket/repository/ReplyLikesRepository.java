package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, Long> {

    void deleteReplyLikesByMemberAndBoardReplyAndBoard(Member member, BoardReply boardReply, Board board);

//    @Query("select p.id, p.content, pi, p.likes, p.modDate " +
//            "from BoardReply p " +
//            "left outer join ReplyLikes l on p = l.boardReply " +
//            "left outer join Board pi on p = pi.boardReply " +
//            "where l.member = :member " +
//            "group by p")
//    List<Object[]> getBoardReplyByReplyLikes(Member member);
}
