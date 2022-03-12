package com.hmsh.carrotmarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private String phoneNumber;

    private String name;

    private String address;
}
