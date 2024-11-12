package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "comment")
//댓글 테이블
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_seq")
    private Long id;
    //내용
    @Column(nullable = false)
    private String content;
    //좋아요 개수
    private int GreatNum;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;
}
