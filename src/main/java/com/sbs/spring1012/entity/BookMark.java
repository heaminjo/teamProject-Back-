package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "bookmark")
//북마크 테이블
public class BookMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookmark_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "board_seq")
    private Board board;
}
