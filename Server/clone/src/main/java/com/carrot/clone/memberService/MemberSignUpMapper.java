package com.carrot.clone.memberService;

import com.carrot.clone.memberDao.MemberDAO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberSignUpMapper {
    void signupMember(MemberDAO memberDAO);
    MemberDAO select();
}
