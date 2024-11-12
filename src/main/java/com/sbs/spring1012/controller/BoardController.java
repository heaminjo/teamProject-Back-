package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.dto.BoardResDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //Rest API에 대한 요청과 응답처리를 위한 어노테이션
@Slf4j
@RequestMapping("/board") //대표경로 지정
@RequiredArgsConstructor //생성자를 통한 의존성 주입
@CrossOrigin(origins = "http/localhost:3000")
public class BoardController {
//    @GetMapping("/write")
//    public ResponseEntity<BoardResDto> write(@RequestBody BoardReqDto boardReqDto){
//
//    }
}
