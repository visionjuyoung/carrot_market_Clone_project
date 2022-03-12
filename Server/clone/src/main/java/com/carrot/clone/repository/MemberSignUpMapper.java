package com.carrot.clone.repository;

import com.carrot.clone.memberDao.MemberDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.lang.reflect.Member;
import java.util.Optional;


public interface MemberSignUpMapper extends JpaRepository<MemberDAO, String> {

    //void signupMember(MemberDAO memberDAO);

    @Query("select m from MemberDAO m")
    Optional<MemberDAO> select();
}
