package com.hmsh.carrotmarket.dto;

import com.hmsh.carrotmarket.enumeration.Address;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class MemberDTO {

    private String phoneNumber;

    private String name;

    private Address address;

    private String uniqueNumber;

    private String filePath;

}
