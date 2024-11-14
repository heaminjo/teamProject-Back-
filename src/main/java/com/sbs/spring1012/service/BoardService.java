package com.sbs.spring1012.service;

import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.entity.Board;
import com.sbs.spring1012.entity.Category;
import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.BoardRepository;
import com.sbs.spring1012.repository.CategoryRepository;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j //로그 메시지 출력
@Service  //스프링 빈 컨테이너에 등록
@RequiredArgsConstructor//생성자를 통해서 의존성 주입을 받기위한 어노테이션
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    public boolean boardInsert(BoardReqDto boardReqDto,String email) {
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow(
                            () -> new RuntimeException("존재하지않는 회원입니다."));
            System.out.println("멤버 있음");
            Category category = categoryRepository.findByCategoryName(boardReqDto.getCategoryName()).orElseThrow(
                    ()-> new RuntimeException("존재하지않는 카테고리입니다."));
            System.out.println("카테고리 있음");
            Board board = new Board();

            board.setMember(member);
            board.setCategory(category);
            board.setTitle(boardReqDto.getTitle());
            board.setContent(boardReqDto.getContent());
            board.setBoardType(boardReqDto.getBoardType());
            board.setImg(boardReqDto.getImg());

            System.out.println(board.getTitle());
            boardRepository.save(board);
            return true;

        } catch (Exception e) {
            System.out.println("에러떳다...");
            return false;
        }
    }
 public boolean boardModify(BoardReqDto boardReqDto){
        try{
            System.out.println("수정한다.");
            Board board = boardRepository.findById(boardReqDto.getId()).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
            System.out.println("게시물 있음.");

            Category category = categoryRepository.findByCategoryName(boardReqDto.getCategoryName()).orElseThrow(
                    ()-> new RuntimeException("존재하지않는 카테고리입니다."));
            System.out.println("카테고리 있음.");

            // 값이 null이 아니면 업데이트, null이면 기존 값 유지
            System.out.println(category.getCategoryName());
            if (boardReqDto.getCategoryName() != null) {
                board.setCategory(category);
            }
            if (boardReqDto.getCategoryName() != null) {
                board.setCategory(category);
            }
            if (boardReqDto.getBoardType() != null) {
                board.setBoardType(boardReqDto.getBoardType());
            }
            if (boardReqDto.getTitle() != null) {
                board.setTitle(boardReqDto.getTitle());
            }
            if (boardReqDto.getImg() != null) {
                board.setImg(boardReqDto.getImg());
            }
            if (boardReqDto.getContent() != null) {
                board.setContent(boardReqDto.getContent());
            }


            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

