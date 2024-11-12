package com.sbs.spring1012.entity;

import com.sbs.spring1012.constant.BoardType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@ToString @Table(name = "board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_seq")
    private Long id;
    //제목
    @Column(nullable = false)
    private String title;
    //내용
    @Column(nullable = false)
    private String content;

    //게시판 종류(일반게시글/Q&A)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    //카테고리
    @ManyToOne
    @JoinColumn(name = "category_seq")
    private Category category;

    //태그
    @Convert(converter = StringListConverter.class)
    private List<String> boardTag;
    //조회수
    private int views;
    //좋아요 개수
    private int greatNum;
}
