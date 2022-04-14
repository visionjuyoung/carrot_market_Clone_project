package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.BoardReply;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import com.hmsh.carrotmarket.entity.ReplyLikes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikesRepository extends JpaRepository<ReplyLikes, Long> {

    void deleteReplyLikesByMemberAndBoardReply(Member member, BoardReply boardReply);

}
