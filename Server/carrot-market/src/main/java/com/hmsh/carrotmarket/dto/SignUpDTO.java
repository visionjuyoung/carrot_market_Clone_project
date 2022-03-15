package com.hmsh.carrotmarket.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class SignUpDTO {
    private String name;

    private String phoneNumber;

    private String townName;
}
