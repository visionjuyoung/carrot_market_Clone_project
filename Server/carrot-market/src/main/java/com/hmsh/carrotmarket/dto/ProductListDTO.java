package com.hmsh.carrotmarket.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductListDTO {

    private Long id;

    private String title;

    private String address;

    private int price;

    private int likes;

    private int chats;

    private LocalDateTime modDate;

}
