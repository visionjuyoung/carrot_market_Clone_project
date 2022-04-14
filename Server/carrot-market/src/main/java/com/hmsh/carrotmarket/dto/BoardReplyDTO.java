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
public class BoardReplyDTO {

    private Long id;

    private String content;

    private String phoneNumber;

    private List<String> imagePathList;

    private Long boardId;

    private Long like;

    private LocalDateTime modDate;

}
