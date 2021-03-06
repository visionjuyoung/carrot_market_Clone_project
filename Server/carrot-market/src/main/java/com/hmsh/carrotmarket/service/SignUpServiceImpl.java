package com.hmsh.carrotmarket.service;


import com.hmsh.carrotmarket.converter.SignUpConverter;
import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.MemberRole;
import com.hmsh.carrotmarket.entity.SignUpMember;
import com.hmsh.carrotmarket.enumeration.Address;
import com.hmsh.carrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Member> getMember(String phoneNumber) {
        Optional<Member> signUpDTO = memberRepository.findById(phoneNumber);

        return signUpDTO;
    }

    @Override
    public boolean memberCheck(String phoneNumber) {
        Optional<Member> signUpDTO = memberRepository.findById(phoneNumber);

        if(signUpDTO.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean signUpMember(SignUpDTO dto, File path) {
        int number = (int) (Math.random() * 100000000);
        String randomNumber = String.format("%08d", number);
        String numberSign = "#";
        String result = numberSign.concat(randomNumber);

        try {
            String password = passwordEncoder.encode(dto.getPhoneNumber());
            Member signUpMember = SignUpConverter.dtoToEntitySignUp(dto, result, password, path);

            memberRepository.save(signUpMember);
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public MemberDTO editMember(EditUserDTO dto, File file) {
        Optional<Member> signUpMember = memberRepository.findById(dto.getPhoneNumber());

        MemberDTO memberDTO = new MemberDTO();

        try {
            if (signUpMember.isPresent()){
                Member member = signUpMember.get();

                member.editMember(dto);
                member.setFilePath(Objects.isNull(file) ? null : file.getPath());
                
                memberRepository.save(member);
                return SignUpConverter.EntityToMemberDTO(member);
            }
        }catch (NullPointerException e){
            return memberDTO;
        }
        return memberDTO;
    }
}
