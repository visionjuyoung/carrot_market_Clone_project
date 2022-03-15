package com.hmsh.carrotmarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private String uuid;
    private String fileName;
    private String contentType;
}
