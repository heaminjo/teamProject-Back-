package com.sbs.spring1012.dto;

import com.sbs.spring1012.entity.Comment;
import com.sbs.spring1012.entity.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString @Builder
public class CommentResDto {
    private Long commentId;
    private Long boardId;
    private String memberAlias;
    private String memberImage;
    private String content;
    private int greatNum;
    private LocalDateTime commentRegDate;

    public static CommentResDto of(Comment comment){
        return CommentResDto.builder()
                .commentId(comment.getId())
                .boardId(comment.getBoard().getId())
                .memberAlias(comment.getMember().getAlias())
                .memberImage(comment.getMember().getImage())
                .content(comment.getContent())
                .greatNum(comment.getGreatNum())
                .commentRegDate(comment.getCreateDate())
                .build();
    }
}
