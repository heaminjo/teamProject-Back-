package com.sbs.spring1012.dto;

import com.sbs.spring1012.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberResDto {
    private String pwd;
    private String email;
    private String alias;
    private String address;
    private String image;
//    private List<String> tag;
    private int follower;
    private int followee;
    private LocalDateTime regDate;

    //of : Entity -> MemberResDto 변환
    public static MemberResDto of(Member member){
        return MemberResDto.builder()
                .pwd(member.getPwd())
                .email(member.getEmail())
                .alias(member.getAlias())
                .address(member.getAddress())
                .image(member.getImage())
//                .tag(member.getTag()) 보류
                .follower(member.getFollower())
                .followee(member.getFollowee())
                .regDate(member.getCreateDate())
                .build();
    }
}
