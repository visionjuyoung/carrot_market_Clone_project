package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.entity.Member;

import java.util.Optional;

public interface MemberService {

    MemberDTO getDTO(String phoneNumber);

    Optional<Member> get(String phoneNumber);

    String register(Member member);
}
