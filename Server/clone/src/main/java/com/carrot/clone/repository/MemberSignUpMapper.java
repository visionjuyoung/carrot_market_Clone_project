package com.carrot.clone.repository;

import com.carrot.clone.memberDao.MemberDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MemberSignUpMapper extends JpaRepository{

    //void signupMember(MemberDAO memberDAO);

    @Query("select * FROM user")
    MemberDAO select();
}
