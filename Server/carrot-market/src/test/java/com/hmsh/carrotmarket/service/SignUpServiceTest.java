package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.MemberRole;
import com.hmsh.carrotmarket.entity.SignUpMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignUpServiceTest {
    @Autowired
    private SignUpService signUpService;

    @Test
    void register() {
        SignUpDTO member = SignUpDTO.builder()
                .phoneNumber("01046054229")
                .name("조현민")
                .address("경기도 여주시")
                .build();

        signUpService.signUpMember(member);
    }
}
