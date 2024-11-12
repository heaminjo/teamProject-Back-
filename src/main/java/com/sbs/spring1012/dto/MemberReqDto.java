package com.sbs.spring1012.dto;

import com.sbs.spring1012.constant.Authority;
import com.sbs.spring1012.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    //toEntity : MemberReqDto -> Entity로 변환
    public Member ToEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                //비밀번호 암호화
                .pwd(pwd)
                .alias(alias)
                .address(address)
                .tag(tag)
                .authority(Authority.ROLE_USER)
                .build();
    }

    //사용자 인증을 처리하기위해 넘겨주어야할 token
    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, pwd);
    }
}
