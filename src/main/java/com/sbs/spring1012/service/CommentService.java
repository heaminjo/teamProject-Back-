package com.sbs.spring1012.service;

import com.sbs.spring1012.dto.BoardResDto;
import com.sbs.spring1012.dto.CommentReqDto;
import com.sbs.spring1012.dto.CommentResDto;
import com.sbs.spring1012.entity.Board;
import com.sbs.spring1012.entity.Comment;
import com.sbs.spring1012.entity.GreatComment;
import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.BoardRepository;
import com.sbs.spring1012.repository.CommentRepository;
import com.sbs.spring1012.repository.GreateCommentRepository;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j //로그 메시지 출력

@Service
@RequiredArgsConstructor
public class CommentService {
    public final CommentRepository commentRepository;
    public final BoardRepository boardRepository;
    public final MemberRepository memberRepository;
    public final GreateCommentRepository greateCommentRepository;
    //댓글 등록
    public boolean newComment(CommentReqDto commentReqDto,Long id){
        try {
            Board board = boardRepository.findById(commentReqDto.getBoardId()).orElseThrow(
                    () -> new RuntimeException("존재하지않는 게시글입니다.")
            );

            Member member = memberRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지않는 회원입니다.")
            );

            Comment comment = new Comment();

            comment.setBoard(board);
            comment.setMember(member);
            comment.setContent(commentReqDto.getContent());

            commentRepository.save(comment);

            return true;
        }catch(Exception e){
            return false;
        }
    }

    //댓글 좋아요
    public boolean greatComment(Long commentId, Long memberId){
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new RuntimeException("존재하지않는 댓글입니다.")
            );
            Member member = memberRepository.findById(memberId).orElseThrow(
                    () -> new RuntimeException("존재하지않는 회원입니다.")
            );

            Optional<GreatComment> greatComment= greateCommentRepository.findByCommentAndMember(comment,member);

            //좋아요가 눌려있다면 좋아요 삭제
            if(greatComment.isPresent()){
                greateCommentRepository.delete(greatComment.get());
                comment.setGreatNum(comment.getGreatNum()-1);
            }
            //안 눌려있다면 좋아요 추가
            else {
                GreatComment newGreate = new GreatComment();
                newGreate.setComment(comment);
                newGreate.setMember(member);

                comment.setGreatNum(comment.getGreatNum()+1);
                greateCommentRepository.save(newGreate);
            }
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //댓글 수정
    public boolean commentModify(CommentReqDto commentReqDto,Long commentId){
        try{
            Comment comment = commentRepository.findById(commentId).orElseThrow(
                    () -> new RuntimeException("존재하지않는 댓글입니다.")
            );

            String content = "";
            //만약에 수정 댓글이 비어있다면
            if (commentReqDto.getContent().isEmpty()){
                content = comment.getContent();
            }else {
                content = commentReqDto.getContent();
            }
            comment.setContent(content);
            commentRepository.save(comment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //댓글 삭제
    public boolean commentDelete(Long id) {
        try {
            Comment comment = commentRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("존재하지않는 댓글입니다.")
            );
            commentRepository.delete(comment);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //댓글상세조회
    public CommentResDto CommentDetail(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지않는 게시글입니다."));

        return CommentResDto.of(comment);
    }
    //댓글 전체 조회
    public List<CommentResDto> getCommentList(){
        List<Comment> comments= commentRepository.findAll();
        List<CommentResDto> list = new ArrayList<>();

        for(Comment comment : comments){
            list.add(CommentResDto.of(comment));
        }
        return list;
    }
}
