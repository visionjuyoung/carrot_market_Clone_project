package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member get(String phoneNumber) {
        Optional<Member> optionalMember = memberRepository.findById(phoneNumber);
        return optionalMember.orElse(null);
    }
}
