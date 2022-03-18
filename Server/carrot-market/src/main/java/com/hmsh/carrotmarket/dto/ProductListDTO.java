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

    private int price;

    private String address;

    private int views;

    private int chatsCount;

    private LocalDateTime modDate;

}
