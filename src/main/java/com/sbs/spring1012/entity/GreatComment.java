package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

//좋아요 기능 보류
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "great_comment")
//좋아요 관계 테이블
public class GreatComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "great_comment_seq")
    private Long id;
    
    //좋아요 누른 멤버
    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;
    
    //좋아요 받은 댓글
    @ManyToOne
    @JoinColumn(name = "comment_seq")
    private Comment comment;
}
