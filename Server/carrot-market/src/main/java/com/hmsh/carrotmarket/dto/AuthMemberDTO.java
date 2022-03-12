package com.hmsh.carrotmarket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Slf4j
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User {

    private String phoneNumber;

    private String name;

    private String address;


    public AuthMemberDTO(String username,
                         String password,
                         String name,
                         String address,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.phoneNumber = username;
        this.name = name;
        this.address = address;
    }
}
