package com.hmsh.carrotmarket.entity;

import com.hmsh.carrotmarket.enumeration.Address;
import lombok.*;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private Address address;

    private String uniqueNumber;

    private String filePath;

    private boolean clickedLike = false;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();


    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
