package com.sbs.spring1012.dto;

import com.sbs.spring1012.entity.Board;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardReqDto {
    private String title;
    private String content;
    private String boardType;
    private List<String> tag;
    private String image;
}


