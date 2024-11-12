package com.sbs.spring1012.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
//팔로우 관계 테이블
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "follow_seq")
    private Long id;

    //팔로워
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private Member follower;

    //팔로윙
    @ManyToOne
    @JoinColumn(name = "followee_id")
    private Member followee;
}
