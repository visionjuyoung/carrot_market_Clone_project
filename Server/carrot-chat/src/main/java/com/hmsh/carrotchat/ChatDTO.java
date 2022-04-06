package com.hmsh.carrotchat;

import lombok.Data;

@Data
public class ChatDTO {

    private Long productId;

    private String sellerId;

    private String senderId;

    private String message;

}
