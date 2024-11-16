package com.sbs.spring1012.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString @Builder
public class CommentReqDto {
    private Long id;
    private Long boardId;
    private String content;
}
