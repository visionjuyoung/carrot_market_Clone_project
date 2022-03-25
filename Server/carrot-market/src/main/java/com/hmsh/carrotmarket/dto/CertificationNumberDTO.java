package com.hmsh.carrotmarket.dto;

import com.hmsh.carrotmarket.enumeration.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CertificationNumberDTO {

    private String phoneNumber;

    private String number;

    private Address address;
}
