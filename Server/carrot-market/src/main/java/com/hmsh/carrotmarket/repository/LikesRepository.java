package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Likes;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    void deleteLikesByMemberAndProduct(Member member, Product product);

    Optional<Likes> getLikesByMemberAndProduct(Member member, Product product);
}
