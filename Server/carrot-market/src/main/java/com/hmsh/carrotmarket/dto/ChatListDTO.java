package com.hmsh.carrotmarket.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatListDTO {
    private String chatId;

    private String message;
}
