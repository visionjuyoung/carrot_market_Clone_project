package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Likes;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    void deleteLikesByMemberAndProduct(Member member, Product product);
}
