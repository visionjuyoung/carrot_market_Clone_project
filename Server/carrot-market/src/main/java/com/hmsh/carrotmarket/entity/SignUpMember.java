package com.hmsh.carrotmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.File;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class SignUpMember {
    @Id
    private String phoneNumber;

    private String address;

    private String name;

    private String uniqueNumber;

    private File file;
}
