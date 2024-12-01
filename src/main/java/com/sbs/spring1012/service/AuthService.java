package com.sbs.spring1012.service;

import com.sbs.spring1012.constant.Authority;
import com.sbs.spring1012.dto.MemberReqDto;
import com.sbs.spring1012.dto.MemberResDto;
import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//회원가입/로그인 서비스
@Slf4j //로그 메시지 출력
@Service  //스프링 빈 컨테이너에 등록
@RequiredArgsConstructor//생성자를 통해서 의존성 주입을 받기위한 어노테이션
@Transactional
public class AuthService {
    private final MemberRepository memberRepository; //의존성 주입
    //회원가입 여부 확인
    //이메일이 이미 등록되어있다면 가입 불가능
    public boolean isMember(String email){
        return memberRepository.existsByEmail(email);
    }

    //회원가입 성공/실패
    public boolean signup(MemberReqDto memberReqDto) {
        try {
            if (isMember(memberReqDto.getEmail())) {
                throw new RuntimeException("이미 존재하는 이메일입니다.");
            }
            Member member = memberCoverEntity(memberReqDto);
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public MemberResDto login(String email, String pwd){
        Member member = memberRepository.findByEmailAndPwd(email,pwd)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));

        //탈퇴 회원 여부
        if(member.isWithdraw()){
            return null;
        }
        return MemberResDto.of(member);
    }

    public Member memberCoverEntity(MemberReqDto memberReqDto){
        Member member = new Member();
        member.setEmail(memberReqDto.getEmail());
        member.setPwd(memberReqDto.getPwd());
        member.setAddress(memberReqDto.getAddress());
        member.setAlias(memberReqDto.getAlias());
        member.setImage(memberReqDto.getImage());
        member.setName(memberReqDto.getName());
        member.setPhone(memberReqDto.getPhone());
//        member.setTag(memberReqDto.getTag());  보류
        member.setWithdraw(false);
        member.setAuthority(Authority.ROLE_USER);

        return member;
    }
}