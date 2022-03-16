package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.converter.MemberConverter;
import com.hmsh.carrotmarket.dto.MemberDTO;
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
    public MemberDTO getDTO(String phoneNumber) {
        Optional<Member> optionalMember = memberRepository.findByPhoneNumber(phoneNumber);
        return optionalMember.map(MemberConverter::memberToMemberDTO).orElse(null);
    }

    @Override
    public Optional<Member> get(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public String register(Member member) {
        Member save = memberRepository.save(member);
        return save.getPhoneNumber();
    }

}
