package com.hmsh.carrotmarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class BoardReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Column(nullable = false)
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public void setContent(String content) {
        this.content = content;
    }
}
