package com.carrot.clone.memberDao;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class MemberDAO {
    @Id
    private String town;
    private String phoneNumber;
}
