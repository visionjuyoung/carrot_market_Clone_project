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

    private Long productId;

    private String sellerId;

    private String senderId;

    private String message;

    private LocalDateTime createdAt;
}
