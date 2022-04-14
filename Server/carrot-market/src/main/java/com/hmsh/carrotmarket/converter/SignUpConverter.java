package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.EditUserDTO;
import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.dto.SignUpDTO;
import com.hmsh.carrotmarket.entity.Member;
import com.hmsh.carrotmarket.enumeration.Address;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.io.File;
import java.util.Objects;

public class SignUpConverter {
    public static Member dtoToEntitySignUp(SignUpDTO dto, String result, String password, File path) {
        return Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .password(password)
                .address(Address.getByRegion(dto.getAddress()))
                .name(dto.getName())
                .uniqueNumber(result)
                .filePath(Objects.isNull(path) ? null : path.getPath())
                .build();
    }

    public static Member dtoToEntityEdit(EditUserDTO dto, File file, String password) {
        return Member.builder()
                .phoneNumber(dto.getPhoneNumber())
                .password(password)
                .address(Address.getByRegion(dto.getAddress()))
                .name(dto.getName())
                .uniqueNumber(dto.getUniqueNumber())
                .filePath(Objects.isNull(file) ? null : file.getPath())
                .build();
    }

    public static MemberDTO EntityToMemberDTO(Member member) {
        return MemberDTO.builder()
                .phoneNumber(member.getPhoneNumber())
                .name(member.getName())
                .address(((Address) member.getAddress()).getRegion())
                .uniqueNumber(member.getUniqueNumber())
                .filePath(Objects.isNull(member.getFilePath()) ? null : member.getFilePath())
                .build();
    }
}
