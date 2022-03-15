package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.SignUpMember;

import java.util.Optional;

public interface SignUpService {

    boolean signUpMember(SignUpDTO dto);
    boolean memberCheck(String phoneNumber);
    Optional<SignUpMember> getMember(String phoneNumber);
}
