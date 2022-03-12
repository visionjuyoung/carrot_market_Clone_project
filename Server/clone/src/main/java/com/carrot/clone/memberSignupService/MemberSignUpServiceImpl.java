package com.carrot.clone.memberSignupService;

import com.carrot.clone.memberDao.MemberDAO;
import com.carrot.clone.repository.MemberSignUpMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberSignUpServiceImpl implements MemberSignUpService{
    private final MemberSignUpMapper memberSignUpMapper;

//    public MemberDAO registerMember(MemberDAO memberDAO){
//        memberSignUpMapper.signupMember(memberDAO);
//        return memberDAO;
//    }
    
    public Optional<MemberDAO> selectMember(){
        Optional<MemberDAO> memberDAO = memberSignUpMapper.select();
        return memberDAO;
    }

}