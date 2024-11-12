package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.MemberReqDto;
import com.sbs.spring1012.dto.MemberResDto;
import com.sbs.spring1012.dto.TokenDto;
import com.sbs.spring1012.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //Rest API에 대한 요청과 응답처리를 위한 어노테이션
@Slf4j
@RequestMapping("/auth") //대표경로 지정
@RequiredArgsConstructor //생성자를 통한 의존성 주입
@CrossOrigin(origins = "http/localhost:3000")
public class AuthController {
    private final AuthService authService;
    //회원가입 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String email){
        boolean isTrue = authService.isMember(email);

        return ResponseEntity.ok(!isTrue);
    }
//회원가입
    @PostMapping("/signup")
    public ResponseEntity<Boolean> signup(@RequestBody MemberReqDto memberReqDto){
        return ResponseEntity.ok(authService.signup(memberReqDto));
    }
//로그인
@PostMapping("/login")
public ResponseEntity<MemberResDto> login(@RequestBody MemberReqDto memberReqDto) {
    log.info("memberDto:{}", memberReqDto);
    return ResponseEntity.ok(authService.login(memberReqDto.getEmail(), memberReqDto.getPwd()));
}
}
