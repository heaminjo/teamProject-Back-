package com.sbs.spring1012.service;

import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.dto.BoardResDto;
import com.sbs.spring1012.entity.Board;
import com.sbs.spring1012.entity.Member;
import com.sbs.spring1012.repository.BoardRepository;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j //로그 메시지 출력
@Service  //스프링 빈 컨테이너에 등록
@RequiredArgsConstructor//생성자를 통해서 의존성 주입을 받기위한 어노테이션
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    //게시글 등럭
//    public Boolean saveBoard(BoardReqDto boardReqDto,Long id){
//        try{
//            Member member = memberRepository.findById(id)
//        }
//    }

    //게시글 -> Entity
//    public Board coverBoardEntity(BoardReqDto boardReqDto){
//        Board board = new Board();
//
//    }

}

