package com.hmsh.carrotmarket.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardReplyListDTO {

    private MemberDTO member;

    private String content;

    private List<String> imagePathList;

    private LocalDateTime modDate;
}
