package com.carrot.clone.controller;

import com.carrot.clone.memberDao.MemberDAO;
import lombok.RequiredArgsConstructor;
import com.carrot.clone.memberSignupService.MemberSignUpService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {
    private final MemberSignUpService memberSignUpService;

//    @RequestMapping(path = "/auth/phone", method = RequestMethod.POST)
//    public MemberDAO home(MemberDAO memberDAO){
//        return memberSignUpService.registerMember(memberDAO);
//    }

    @RequestMapping(value = "/select", method = RequestMethod.GET)
    public Optional<MemberDAO> select(){
        return memberSignUpService.selectMember();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String Test(){
        return "Hello";
    }
}
