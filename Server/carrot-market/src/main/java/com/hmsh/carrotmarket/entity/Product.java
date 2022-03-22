package com.hmsh.carrotmarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
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

    // TODO: 임시로 String 사용, 동네 카테고리 생성 후 변경
    @Column(nullable = false)
    private String address; // 동네

    @Column(nullable = false)
    private int views; // 조회수

    @Column(nullable = false)
    private int chats; // 채팅수

    @Column(nullable = false)
    private int likes; // 좋아요

    // TODO: 카테고리 추가, 거래 상태 추가
    @ManyToOne(fetch = FetchType.LAZY)
    Member member; // 작성자
}
