package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

}
