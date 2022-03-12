package com.carrot.clone.memberSignupService;

import com.carrot.clone.memberDao.MemberDAO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface MemberSignUpService {
    //MemberDAO registerMember(MemberDAO memberDAO);
    Optional<MemberDAO> selectMember();
}