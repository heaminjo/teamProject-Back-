package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "meet_part")
public class MeetPart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meetpart_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_seq")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "meetroom_seq")
    private MeetRoom meetroom;

}
