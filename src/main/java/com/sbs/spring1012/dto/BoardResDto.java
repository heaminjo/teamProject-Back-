package com.sbs.spring1012.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardResDto {
    private String title;
    private String content;
    private String boardType;
//    private List<String> tag; (보류)
    private int views;        //조회수
    private int greatNum;     //좋아요
    private String img;
    private LocalDateTime regDate;
}
