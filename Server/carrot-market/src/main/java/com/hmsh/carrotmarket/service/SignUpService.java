package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.SignUpMember;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

public interface SignUpService {

    boolean signUpMember(SignUpDTO dto, File file);
    boolean memberCheck(String phoneNumber);
    Optional<SignUpMember> getMember(String phoneNumber);
}
