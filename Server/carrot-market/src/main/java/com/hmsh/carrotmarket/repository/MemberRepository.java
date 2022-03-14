package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.phoneNumber = :phoneNumber")
    Optional<Member> findByPhoneNumber(String phoneNumber);

    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.address = :address")
    Optional<Member> findByAddress(String address);
}
