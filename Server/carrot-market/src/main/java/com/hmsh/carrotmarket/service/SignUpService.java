package com.hmsh.carrotmarket.service;

import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;

import java.io.File;
import java.util.Optional;

public interface SignUpService {

    boolean signUpMember(SignUpDTO dto, File file);
    boolean memberCheck(String phoneNumber);
    Optional<Member> getMember(String phoneNumber);
    boolean editMember(EditUserDTO dto, File file);
}
