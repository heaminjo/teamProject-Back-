package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatroom_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

}