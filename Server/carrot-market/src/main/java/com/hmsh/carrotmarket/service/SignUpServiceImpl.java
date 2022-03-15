package com.hmsh.carrotmarket.service;


import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.SignUpMember;
import com.hmsh.carrotmarket.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final SignUpRepository signUpRepository;

    @Override
    public Optional<SignUpMember> getMember(String phoneNumber) {
        Optional<SignUpMember> signUpDTO = signUpRepository.findById(phoneNumber);

        return signUpDTO;
    }

    @Override
    public boolean memberCheck(String phoneNumber) {
        Optional<SignUpMember> signUpDTO = signUpRepository.findById(phoneNumber);

        if(signUpDTO.isPresent()){
            return true;
        }
        else{
            return false;
        }
    }


    @Override
    public boolean signUpMember(SignUpDTO dto) {
        int number = (int) (Math.random() * 10000);
        String randomNumber = String.format("%08d", number);
        String numberSign = "#";
        String result = numberSign.concat(randomNumber);

        try {
            SignUpMember signUpMember = SignUpMember.builder()
                    .phoneNumber(dto.getPhoneNumber())
                    .townName(dto.getTownName())
                    .name(dto.getName())
                    .uniqueNumber(result)
                    .build();

            signUpRepository.save(signUpMember);
            return true;
        }catch (NullPointerException e){
            return false;
        }

    }
}
