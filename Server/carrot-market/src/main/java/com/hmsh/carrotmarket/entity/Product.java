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

    private String title; // 제목

    private String content; // 글

    private int price; // 가격

    // TODO: 임시로 String 사용, 동네 카테고리 생성 후 변경
    private String address; // 동네

    private int likes; // 관심

    private int views; // 조회수

    private int chats; // 채팅수

    // TODO: 이미지, 카테고리 추가

    @ManyToOne(fetch = FetchType.LAZY)
    Member member; // 작성자
}
