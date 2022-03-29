package com.hmsh.carrotchat;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "room")
public class Room {

    @Id
    private String id;

    private Long productId;

    private String seller;

    private String buyer;
}
