package com.carrot.clone.repository;

import com.carrot.clone.memberDao.MemberDAO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.lang.reflect.Member;


public interface MemberSignUpMapper extends JpaRepository<Member, String> {

    //void signupMember(MemberDAO memberDAO);

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT town, phoneNumber FROM user")
    MemberDAO select();
}
