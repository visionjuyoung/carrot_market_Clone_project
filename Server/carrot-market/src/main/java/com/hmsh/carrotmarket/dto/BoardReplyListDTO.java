package com.hmsh.carrotmarket.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardReplyListDTO {

    private MemberDTO member;

    private String content;

    private LocalDateTime modDate;
}
