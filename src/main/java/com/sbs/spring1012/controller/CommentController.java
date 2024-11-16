package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.BoardResDto;
import com.sbs.spring1012.dto.CommentReqDto;
import com.sbs.spring1012.dto.CommentResDto;
import com.sbs.spring1012.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment") //대표경로 지정
@RequiredArgsConstructor //생성자를 통한 의존성 주입
@CrossOrigin(origins = "http/localhost:3000")
public class CommentController {
    private final CommentService commentService;

    //댓글 등록
    @GetMapping("/new/{id}")
    public ResponseEntity<Boolean> newComment(@RequestBody CommentReqDto commentReqDto,@PathVariable Long id){
        Boolean isTrue = commentService.newComment(commentReqDto,id);
        return ResponseEntity.ok(isTrue);
    }

    //댓글 좋아요
    @GetMapping("/great/{commentId}/{memberId}")
    public ResponseEntity<Boolean> greatComment(@PathVariable Long commentId, @PathVariable Long memberId){
        Boolean isTrue = commentService.greatComment(commentId,memberId);
        return ResponseEntity.ok(isTrue);
    }

    //댓글 수정
    @GetMapping("/modify/{commentId}")
    public ResponseEntity<Boolean> commentModify(@RequestBody CommentReqDto commentReqDto,@PathVariable Long commentId){
        Boolean isTrue = commentService.commentModify(commentReqDto,commentId);
        return ResponseEntity.ok(isTrue);
    }

    //댓글 삭제
    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> commentDelete(@PathVariable Long id) {
        Boolean isTrue = commentService.commentDelete(id);
        return ResponseEntity.ok(isTrue);
    }

    // 댓글 상세조회
    @PostMapping("/detail/{id}")
    public ResponseEntity<CommentResDto> commentDetail(@PathVariable Long id){
        CommentResDto commentResDto = commentService.CommentDetail(id);
        return ResponseEntity.ok(commentResDto);
    }

    //게시글 전제조회
    @PostMapping("/list")
    public ResponseEntity<List<CommentResDto>> commentAll(){
        List<CommentResDto> list = commentService.getCommentList();
        return ResponseEntity.ok(list);
    }

}