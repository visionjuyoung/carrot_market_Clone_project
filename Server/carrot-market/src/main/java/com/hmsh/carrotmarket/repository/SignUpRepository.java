package com.hmsh.carrotmarket.repository;

import com.hmsh.carrotmarket.entity.SignUpMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepository extends JpaRepository<SignUpMember, String> {
}
