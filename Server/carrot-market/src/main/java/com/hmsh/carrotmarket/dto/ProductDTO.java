package com.hmsh.carrotmarket.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDTO {

    private String title;

    private int price;

    private String content;

    private String address;

    private int views;

    private int chatsCount;

    private String phoneNumber;

    private MemberDTO memberDTO;

}
