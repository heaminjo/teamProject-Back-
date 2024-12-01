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

    //게시글 전체조회
    @GetMapping("/list")
    public ResponseEntity<List<BoardResDto>> boardList(){
        List<BoardResDto> boardList = boardService.getBoardAll();
        return ResponseEntity.ok(boardList);
    }
    //총 페이지 수 조회
    @GetMapping("/totalpages")
    public ResponseEntity<Integer> listBoardPage(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryName) {

        int totalPages = boardService.getTotalPage(page,size,keyword,categoryName);
        return ResponseEntity.ok(totalPages);
    }
    //게시글 목록 (페이지네이션
    @GetMapping("/list/page")
    public ResponseEntity<List<BoardResDto>> getBoardList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "recent") String sort, //기본값은 최신순
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryName
   ){
        List<BoardResDto> boardList = boardService.getPageBoardList(page,size,sort,keyword,categoryName);
        return ResponseEntity.ok(boardList);
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

