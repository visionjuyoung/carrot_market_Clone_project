package com.hmsh.carrotmarket.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDTO {

    private Long id;

    private String title;

    private int price;

    private String content;

    private String address;

    private int views;

    private int chatsCount;

    private String phoneNumber;

    private LocalDateTime modDate;

    private MemberDTO memberDTO;

}
