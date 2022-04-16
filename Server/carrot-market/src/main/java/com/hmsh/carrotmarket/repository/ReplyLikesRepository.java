package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, Long> {

    void deleteReplyLikesByMemberAndBoardReply(Member member, BoardReply boardReply, Board board);

}
