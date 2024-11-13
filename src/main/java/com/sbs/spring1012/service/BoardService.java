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
            System.out.println("member저장!");
            board.setCategory(category);
            System.out.println("cate저장!");
            board.setTitle(boardReqDto.getTitle());
            System.out.println("title 저장");
            board.setContent(boardReqDto.getContent());
            System.out.println("content 저장!");
            board.setBoardType(board.getBoardType());
            System.out.println("type저장!");
            board.setImg(board.getImg());
            System.out.println("img저장!");

            System.out.println(board.getTitle());
            boardRepository.save(board);

            System.out.println("저장!");
            return true;

        } catch (Exception e) {
            System.out.println("에러떳다...");
            return false;
        }
    }

}

