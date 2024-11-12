package com.sbs.spring1012.entity;

import com.sbs.spring1012.constant.Authority;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "pwd") // pwd는 제외하고 출력
@Table(name = "member")
public class Member extends BaseDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_seq")
    private Long id;

    //이메일
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    //패스워드
    @Column(name = "pwd",nullable = false)
    private String pwd;

    //별명
    @Column(name = "alias",nullable = false,unique = true,length = 8)
    private String alias;

    //주소
    @Column(name = "address",nullable = false)
    private String address;

    //관심태그
    @Convert(converter = StringListConverter.class)
    private List<String> tag;

    //팔로워 수
    private int follower;

    //팔로윙 수
    private int followee;

    private String image;
    //탈퇴여부
    @Column(name = "is_withdraw")
    private boolean isWithdraw;

    //spring Security에는 member의 role이 필요
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public Member(String email,String pwd,String alias,String address,
                   List<String>tag,int follower,int followee,
                  Authority authority,String image
    ){
        this.email = email;
        this.pwd = pwd;
        this.alias = alias;
        this.address = address;
        this.tag = tag;
        this.follower = follower;
        this.followee = followee;
        this.authority = authority;
        this.image = image;
    }

}
