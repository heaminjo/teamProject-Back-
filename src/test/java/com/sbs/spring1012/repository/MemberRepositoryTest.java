package com.sbs.spring1012.repository;

import com.sbs.spring1012.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;
    public Member createMember(){
        Member member = new Member();

        member.setEmail("joheamin");
        member.setPwd("geanea");
        member.setAddress("성남");
        member.setAlias("조조");

        return member;
    }

    @Test
    public void saveMemberTest(){
        Member member = createMember();

        Member savedMember = memberRepository.save(member);
        System.out.println(savedMember.toString());
    }
    @Test
    @DisplayName("회원검색")
    public void userSearch(){
        memberRepository.save(createMember());

        Member member = memberRepository.findByAlias("조조").orElseThrow(()-> new RuntimeException("찾으시는 회원이 없습니다."));
        System.out.println(member.getEmail());
        System.out.println(member.getAlias());
    }
}