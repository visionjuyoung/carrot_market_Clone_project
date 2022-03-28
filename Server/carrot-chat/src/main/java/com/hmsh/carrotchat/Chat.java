package com.hmsh.carrotchat;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "chat")
@Builder
public class Chat {

    @Id
    private String id;

    private String roomId;

    private Long productId;

    private String sender;

    private String receiver;

    private String message;

    private LocalDateTime createdAt;
}
