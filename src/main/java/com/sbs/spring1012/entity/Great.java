package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "great")
//좋아요 관계 테이블
public class Great {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "great_seq")
    private Long id;
    
    //좋아요 누른 멤버
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
    
    //좋아요 받은 게시글
    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;
}
