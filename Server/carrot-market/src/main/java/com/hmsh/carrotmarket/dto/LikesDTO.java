package com.hmsh.carrotmarket.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LikesDTO {

    private String phoneNumber;

    private Long productId;

    private Long boardId;

    private Long boardReplyId;
}
