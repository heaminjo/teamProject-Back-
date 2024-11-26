package com.sbs.spring1012.service;

import com.sbs.spring1012.dto.MemberReqDto;
import com.sbs.spring1012.dto.MemberResDto;
import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j //로그 메시지 출력
@Service  //스프링 빈 컨테이너에 등록
@RequiredArgsConstructor//생성자를 통해서 의존성 주입을 받기위한 어노테이션
//UserDetailService : 데이터베이스에서 회원정보를 가져오는 역할4
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
//    //회원검색
//    public MemberResDto userSearch(String alias){
//        Optional<Member> member = memberRepository.findByAlias(alias);
//        //member에 값이 있다면 dto로 변환하여 방환 아니라면 null반환
//        return member.map(MemberResDto::of).orElse(null);
//    }
    // 회원 상세 조회
    public MemberResDto getMemberDetail(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("해당 회원이 존재하지 않습니다."));
        return MemberResDto.of(member);
    }
    //회원 수정
    public boolean memberModify(MemberReqDto memberReqDto){
        try{
            Member member = memberRepository.findByEmail(memberReqDto.getEmail())
                    .orElseThrow(()->new RuntimeException("존재하지않는 회원입니다."));
            member.setPwd(memberReqDto.getPwd());
            member.setPhone(memberReqDto.getPhone());
            member.setAlias(memberReqDto.getAlias());
            member.setAddress(memberReqDto.getAddress());
            member.setImage(memberReqDto.getImage());
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //회원탈퇴
    public boolean memberSecession(String email){
        try{
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(()-> new RuntimeException("존재하지않는 회원입니다."));

            member.setWithdraw(true);
            member.setEmail(member.getEmail().concat("-"));
            member.setAlias("");
            member.setImage("");
            member.setPhone("");
            member.setName("");
            member.setFollowee(0);
            member.setFollower(0);
            member.setAddress("");
            memberRepository.save(member);
            return true;
        }catch(Exception e){
            return false;
        }

    }










}
