package com.sbs.spring1012.controller;


import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.service.BoardService;
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
    private final BoardService boardService;
    //게시글 등록
    @PostMapping("/insert/{email}")
    public ResponseEntity<Boolean> boardSave(@RequestBody BoardReqDto boardReqDto,@PathVariable String email){
        Boolean isTrue = boardService.boardInsert(boardReqDto,email);
        return ResponseEntity.ok(isTrue);
    }
    //게시글 수정
    @PostMapping("/modify")
    public ResponseEntity<Boolean> boardModify(@RequestBody BoardReqDto boardReqDto){
        Boolean isTrue = boardService.boardModify(boardReqDto);
        return ResponseEntity.ok(isTrue);
    }
}
