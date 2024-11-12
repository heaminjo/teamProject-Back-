package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "chatpart")
public class ChatPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chatpart_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;


}
