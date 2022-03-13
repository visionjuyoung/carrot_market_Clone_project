package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.entity.Member;

import java.util.Optional;

public interface MemberService {

    Optional<Member> get(String phoneNumber);

    String register(Member member);
}
