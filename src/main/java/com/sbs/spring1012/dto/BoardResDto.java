package com.sbs.spring1012.dto;

import com.sbs.spring1012.entity.Board;
import com.sbs.spring1012.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardResDto {
    private Long id;
    private String title;
    private String content;
    private String categoryName;
//    private List<String> tag; (보류)
    private int views;        //조회수
    private int greatNum;     //좋아요
    private String img;
    private String memberAlias; //멤버아이디
    private String memberImage;
    private LocalDateTime regDate;

    public static BoardResDto of(Board board){
        return BoardResDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .greatNum(board.getGreatNum())
                .views(board.getViews())
//                .tag(member.getTag()) 보류
                .img(board.getImg())
                .memberAlias(board.getMember().getAlias())
                .memberImage(board.getMember().getImage())
                .categoryName(board.getCategory().getCategoryName())
                .regDate(board.getCreateDate())
                .build();
    }

}
