package com.hmsh.carrotmarket.entity;

import com.hmsh.carrotmarket.enumeration.Address;
import com.hmsh.carrotmarket.enumeration.TradeStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(exclude = "member")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 글

    @Column(nullable = false)
    private int price; // 가격

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Address address; // 동네

    @Column(nullable = false)
    private int views; // 조회수

    @Column(nullable = false)
    private int chats; // 채팅수

    @Column(nullable = false)
    private int likes; // 좋아요

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TradeStatus tradeStatus; // 거래 상태

    // TODO: 카테고리 추가

    @ManyToOne(fetch = FetchType.LAZY)
    Member member; // 작성자
}
