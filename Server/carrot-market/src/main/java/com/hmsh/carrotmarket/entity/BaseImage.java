package com.hmsh.carrotmarket.entity;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public abstract class BaseImage {

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false)
    private String imgName;

    @Column(nullable = false)
    private String path;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}
