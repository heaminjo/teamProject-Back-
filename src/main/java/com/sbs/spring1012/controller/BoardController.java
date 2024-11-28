package com.sbs.spring1012.controller;


import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.dto.BoardResDto;
import com.sbs.spring1012.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //Rest API에 대한 요청과 응답처리를 위한 어노테이션
@Slf4j
@RequestMapping("/board") //대표경로 지정
@RequiredArgsConstructor //생성자를 통한 의존성 주입
@CrossOrigin(origins = "http://localhost:3000")
public class BoardController {
    private final BoardService boardService;
    //게시글 등록
    @PostMapping("/insert/{email}")
    public ResponseEntity<Boolean> boardSave(@RequestBody BoardReqDto boardReqDto,@PathVariable String email){
        Boolean isTrue = boardService.boardInsert(boardReqDto,email);
        return ResponseEntity.ok(isTrue);
    }
    //게시글 수정
    @PostMapping("/modify/{id}")
    public ResponseEntity<Boolean> boardModify(@RequestBody BoardReqDto boardReqDto,@PathVariable Long id){
        Boolean isTrue = boardService.boardModify(boardReqDto,id);
        return ResponseEntity.ok(isTrue);
    }

    //게시글 삭제
    @PostMapping("/delete/{id}")
    public ResponseEntity<Boolean> boardDelete(@PathVariable Long id){
        Boolean isTrue = boardService.boardDelete(id);
        return ResponseEntity.ok(isTrue);
    }

    //게시글 상세조회
    @PostMapping("/detail/{id}")
    public ResponseEntity<BoardResDto> boardDetail(@PathVariable Long id){
        BoardResDto boardResDto = boardService.boardDetail(id);
        return ResponseEntity.ok(boardResDto);
    }

    //게시글 전제조회
    @PostMapping("/list")
    public ResponseEntity<List<BoardResDto>> boardAll(){
        List<BoardResDto> list = boardService.getBoardList();
        return ResponseEntity.ok(list);
    }

    //게시글 조회 페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<BoardResDto>> boardList(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "4") int size){
        List<BoardResDto> boardList = boardService.getBoardList(page,size);
        return ResponseEntity.ok(boardList);
    }
    //페이지 수 조회
    @GetMapping("/count")
    public ResponseEntity<Integer> listBoards(@RequestParam(defaultValue = "0")int page,
                                              @RequestParam(defaultValue = "4") int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Integer pageCnt = boardService.getBoards(pageRequest);
        return ResponseEntity.ok(pageCnt);
    }
    //조회수
    @GetMapping("detail/view/{id}")
    public ResponseEntity<Boolean> boardView(@PathVariable Long id){
        Boolean isTrue = boardService.viewCount(id);
        return ResponseEntity.ok(isTrue);
    }

    //게시글 좋아요
    @GetMapping("detail/great/{boardId}/{memberId}")
    public ResponseEntity<Boolean> boardGreat(@PathVariable Long boardId,@PathVariable Long memberId){
        Boolean isTrue = boardService.greatBoard(boardId,memberId);
        return ResponseEntity.ok(isTrue);
    }
}

