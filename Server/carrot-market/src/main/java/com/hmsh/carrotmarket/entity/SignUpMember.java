package com.hmsh.carrotmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignUpMember {
    @Id
    private String phoneNumber;

    private String townName;

    private String name;

    private String uniqueNumber;
}
