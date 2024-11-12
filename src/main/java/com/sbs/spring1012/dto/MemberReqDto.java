package com.sbs.spring1012.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
//멤버입력
@Builder
public class MemberReqDto {
    private String pwd;
    private String email;
    private String alias;
    private String address;
    private List<String> tag;
    private String image;

    //toEntity : MemberReqDto -> Entity로 변환
//    public Member ToEntity(PasswordEncoder passwordEncoder){
//        return Member.builder()
//                .email(email)
//                //비밀번호 암호화
//                .pwd(pwd)
//                .alias(alias)
//                .address(address)
//                .tag(tag)
//                .image(image)
//                .authority(Authority.ROLE_USER)
//                .build();
//    }

    //사용자 인증을 처리하기위해 넘겨주어야할 token
//    public UsernamePasswordAuthenticationToken toAuthentication() {
//        return new UsernamePasswordAuthenticationToken(email, pwd);
//    }
}
