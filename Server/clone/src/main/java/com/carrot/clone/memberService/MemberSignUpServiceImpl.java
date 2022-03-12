package com.carrot.clone.memberService;

import com.carrot.clone.memberDao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignUpServiceImpl implements MemberSignUpService{
    private MemberSignUpMapper memberSignUpMapper;

    public MemberDAO registerMember(MemberDAO memberDAO){
        memberSignUpMapper.signupMember(memberDAO);
        return memberDAO;
    }

    public MemberDAO selectMember(){
        MemberDAO memberDAO = memberSignUpMapper.select();
        return memberDAO;
    }

}
