package com.hmsh.carrotmarket.dto;

import com.hmsh.carrotmarket.enumeration.Address;
import com.hmsh.carrotmarket.enumeration.TradeStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    private Address address;

    private int views;

    private int chats;

    private int likes;

    private String phoneNumber;

    private LocalDateTime modDate;

    private List<String> imagePathList;

    private TradeStatus tradeStatus;

    private MemberDTO member;

}
