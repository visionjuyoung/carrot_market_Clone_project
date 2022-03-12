package com.carrot.clone.controller;

import com.carrot.clone.memberDao.MemberDAO;
import com.carrot.clone.memberService.MemberSignUpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final MemberSignUpServiceImpl memberSignUpService;

    @RequestMapping(path = "/auth/phone", method = RequestMethod.POST)
    public MemberDAO home(MemberDAO memberDAO){
        return memberSignUpService.registerMember(memberDAO);
    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public MemberDAO select(){
        return memberSignUpService.selectMember();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Test(){
        return "Hello";
    }
}
