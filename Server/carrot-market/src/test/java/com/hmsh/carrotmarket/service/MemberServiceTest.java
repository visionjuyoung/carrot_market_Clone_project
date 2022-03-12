package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.MemberRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void register() {
        Member member = Member.builder()
                .name("sunghoon")
                .address("sunbu")
                .password(passwordEncoder.encode("password"))
                .phoneNumber("01093010512")
                .build();

        member.addMemberRole(MemberRole.USER);

        memberService.register(member);
    }

}