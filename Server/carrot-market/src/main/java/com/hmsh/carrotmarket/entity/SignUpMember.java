package com.hmsh.carrotmarket.entity;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.File;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class SignUpMember {
    @Id
    private String phoneNumber;

    private String address;

    private String name;

    private String uniqueNumber;

    private String filePath;
}
