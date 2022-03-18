package com.hmsh.carrotmarket.service;


import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.FileDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.SignUpMember;
import com.hmsh.carrotmarket.repository.SignUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public boolean signUpMember(SignUpDTO dto, File path) {
        int number = (int) (Math.random() * 100000000);
        String randomNumber = String.format("%08d", number);
        String numberSign = "#";
        String result = numberSign.concat(randomNumber);

        try {
            SignUpMember signUpMember = SignUpMember.builder()
                    .phoneNumber(dto.getPhoneNumber())
                    .address(dto.getAddress())
                    .name(dto.getName())
                    .uniqueNumber(result)
                    .filePath(path.getName())
                    .build();

            signUpRepository.save(signUpMember);
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }

    @Override
    public boolean editMember(EditUserDTO dto, File file) {
        Optional<SignUpMember> signUpMember = signUpRepository.findById(dto.getPhoneNumber());

        try {
            if (signUpMember.isPresent()){
                SignUpMember newMember = SignUpMember.builder()
                        .phoneNumber(dto.getPhoneNumber())
                        .address(dto.getAddress())
                        .name(dto.getName())
                        .uniqueNumber(dto.getUniqueNumber())
                        .filePath(file.getName())
                        .build();
                signUpRepository.save(newMember);
                return true;
            }
        }catch (NullPointerException e){
            return false;
        }
        return false;
    }
}
