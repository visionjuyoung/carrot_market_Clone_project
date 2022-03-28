package com.hmsh.carrotchat;

import lombok.Data;

@Data
public class ChatDTO {

    private Long productId;

    private String sender;

    private String receiver;

    private String message;

}
