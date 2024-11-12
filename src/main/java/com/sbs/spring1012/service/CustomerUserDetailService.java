package com.sbs.spring1012.service;

import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    // 로그인 시 이메일을 통해 DB에서 회원 정보를 가져온다. createUserDetails() 메서드를 통해 UserDetails 타입으로 변환한다.
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return memberRepository.findByEmail(username)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException(username + "을 DB에서 찾을 수 없습니다."));
//    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 기존 회원정보 조회
        Optional<Member> memberOptional = memberRepository.findByEmail(username);
        if (memberOptional.isPresent()) {
            return createUserDetails(memberOptional.get());
        }

        // 회원 정보에 없으면 관리자 정보 조회
//        Optional<Admin> adminOptional = adminRepository.findById(username);
//        if (adminOptional.isPresent()) {
//            return createAdminDetails(adminOptional.get());
//        }

        // 둘다 없으면 예외 처리
        throw new UsernameNotFoundException(username + "을 DB에서 찾을 수 없습니다.");
    }

    // DB에서 가져온 회원 정보를 UserDetails 타입으로 변환한다.
    private UserDetails createUserDetails(Member member) {
        // 권한 정보를 문자열로 변환
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());
        // UserDetails 타입의 객체를 생성해 리턴
        return new User(
                String.valueOf(member.getId()),
                member.getPwd(),
                Collections.singleton(grantedAuthority)
        );
    }
}