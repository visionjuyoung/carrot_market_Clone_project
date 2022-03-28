package com.hmsh.carrotchat;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "room")
public class Room {

    @Id
    private String id;

    private Long productId;

    private String seller;

    private String buyer;
}
