package com.hmsh.carrotmarket.security.service;

import com.hmsh.carrotmarket.dto.AuthMemberDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberUserDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberService.get(username);

        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("존재하지 않는 ID");
        }

        Member member = optionalMember.get();

        log.info("login member = {}", member);

        return new AuthMemberDTO(
                member.getPhoneNumber(),
                member.getPassword(),
                member.getName(),
                member.getAddress(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList())
        );
    }
}
