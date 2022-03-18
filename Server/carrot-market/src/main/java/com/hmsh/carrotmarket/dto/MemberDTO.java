package com.hmsh.carrotmarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO {

    private String phoneNumber;

    private String name;

    private String address;
}
