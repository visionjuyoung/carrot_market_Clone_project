package com.hmsh.carrotmarket.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class Member {

    @Id
    private String phoneNumber;

    private String password;

    private String name;

    private String address;

}
