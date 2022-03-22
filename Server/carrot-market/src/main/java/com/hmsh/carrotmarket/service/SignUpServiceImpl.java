package com.hmsh.carrotmarket.service;


import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.entity.MemberRole;
import com.hmsh.carrotmarket.entity.SignUpMember;
import com.hmsh.carrotmarket.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final MemberRepository memberRepository;

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
            Member signUpMember = Member.builder()
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .name(dto.getName())
                    .uniqueNumber(result)
                    .filePath(path.getPath() + path.getName())
                    .build();

            memberRepository.save(signUpMember);
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean editMember(EditUserDTO dto, File file) {
        Optional<Member> signUpMember = memberRepository.findById(dto.getPhoneNumber());

        try {
            if (signUpMember.isPresent()){
                Member newMember = Member.builder()
                        .phoneNumber(dto.getPhoneNumber())
                        .password(dto.getPhoneNumber())
                        .address(dto.getAddress())
                        .name(dto.getName())
                        .uniqueNumber(dto.getUniqueNumber())
                        .filePath(file.getPath() + file.getName())
                        .build();
                newMember.addMemberRole(MemberRole.USER);
                memberRepository.save(newMember);
                return true;
            }
        }catch (NullPointerException e){
            return false;
        }
        return false;
    }
}
