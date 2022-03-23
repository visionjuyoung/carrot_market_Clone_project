package com.hmsh.carrotmarket.converter;

import com.hmsh.carrotmarket.dto.MemberDTO;
import com.hmsh.carrotmarket.entity.Member;

public class MemberConverter {

    public static MemberDTO memberToMemberDTO(Member member) {
        return MemberDTO.builder()
                .phoneNumber(member.getPhoneNumber())
                .name(member.getName())
                .address(member.getAddress())
                .uniqueNumber(member.getUniqueNumber())
                .filePath(member.getFilePath())
                .build();
    }
}
