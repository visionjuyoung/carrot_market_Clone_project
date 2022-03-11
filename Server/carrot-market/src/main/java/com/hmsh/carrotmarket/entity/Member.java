package com.hmsh.carrotmarket.entity;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
