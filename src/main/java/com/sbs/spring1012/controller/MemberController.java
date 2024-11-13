package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.MemberReqDto;
import com.sbs.spring1012.dto.MemberResDto;
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
//@GetMapping("/detail/{email}")
//public ResponseEntity<MemberResDto> memberDetail(){
//    log.info("id : {} ", id);
//    MemberResDto memberResDto = memberService.getMemberDetail(email);
//    return ResponseEntity.ok(memberResDto);
//}
    //회원 수정
    @PutMapping("/modify")
    public ResponseEntity<Boolean> memberModify(@RequestBody  MemberReqDto memberReqDto){
        Boolean isTrue = memberService.memberModify(memberReqDto);
        return ResponseEntity.ok(isTrue);
    }
    //회원탈퇴
    @PostMapping("/secession")
    public ResponseEntity<Boolean> memberSecession(@PathVariable String email){
        Boolean isTrue = memberService.memberSecession(email);
        return ResponseEntity.ok(isTrue);
    }
}
