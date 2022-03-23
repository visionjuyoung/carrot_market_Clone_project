package com.hmsh.carrotmarket.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class LoginMemberInfoDTO extends MemberDTO {

    private String token;

}
