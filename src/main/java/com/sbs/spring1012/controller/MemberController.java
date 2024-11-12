package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.MemberResDto;
import com.sbs.spring1012.security.SecurityUtil;
import com.sbs.spring1012.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/member") //대표경로 지정
@RequiredArgsConstructor //생성자를 통한 의존성 주입
@CrossOrigin(origins = "http/localhost:3000")

public class MemberController {
    private final MemberService memberService;

    //회원 검색(별명으로 검색)
    @GetMapping("/search/{alias}")
    public ResponseEntity<MemberResDto> userSearch(@PathVariable String alias){
        MemberResDto memberResDto = memberService.userSearch(alias);
        return ResponseEntity.ok(memberResDto);
    }
    // 회원 상세 조회
@GetMapping("/detail")
public ResponseEntity<MemberResDto> memberDetail(){
    Long id = SecurityUtil.getCurrentMemberId();
    log.info("id : {} ", id);
    MemberResDto memberResDto = memberService.getMemberDetail(id);
    return ResponseEntity.ok(memberResDto);
}


}
