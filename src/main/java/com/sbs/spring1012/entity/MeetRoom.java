package com.sbs.spring1012.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "meetroom")
public class MeetRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meetroom")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String topik;

    @Column(nullable = false)
    private int limits;

    @Column(nullable = false)
    private int current;

    @Column(nullable = false)
    private String content;
}
