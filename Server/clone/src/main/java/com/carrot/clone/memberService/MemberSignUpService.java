package com.carrot.clone.memberService;

import com.carrot.clone.memberDao.MemberDAO;

public interface MemberSignUpService {
    //MemberDAO registerMember(MemberDAO memberDAO);
    MemberDAO selectMember();
}
