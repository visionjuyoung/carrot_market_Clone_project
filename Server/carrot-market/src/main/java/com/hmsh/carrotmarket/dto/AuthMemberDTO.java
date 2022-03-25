package com.hmsh.carrotmarket.dto;

import com.hmsh.carrotmarket.enumeration.Address;
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

    private Address address;

    private String uniqueNumber;

    private String filePath;

    public AuthMemberDTO(String username,
                         String password,
                         String name,
                         Address address,
                         String uniqueNumber,
                         String filePath,
                         Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.phoneNumber = username;
        this.name = name;
        this.address = address;
        this.uniqueNumber = uniqueNumber;
        this.filePath = filePath;
    }
}
