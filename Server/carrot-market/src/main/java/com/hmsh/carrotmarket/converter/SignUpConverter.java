package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.io.File;

public class SignUpConverter {
    public static Member dtoToEntitySignUp(SignUpDTO dto, String result, String password, File path) {
        return Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .password(password)
                .address(dto.getAddress())
                .name(dto.getName())
                .uniqueNumber(result)
                .filePath(path.getPath() + path.getName())
                .build();
    }

    public static Member dtoToEntityEdit(EditUserDTO dto, File file, String password) {
        return Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .password(password)
                .address(dto.getAddress())
                .name(dto.getName())
                .uniqueNumber(dto.getUniqueNumber())
                .filePath(file.getPath() + file.getName())
                .build();
    }
}
