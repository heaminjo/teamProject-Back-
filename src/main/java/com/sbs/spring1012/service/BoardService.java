package com.sbs.spring1012.service;

import com.sbs.spring1012.dto.BoardReqDto;
import com.sbs.spring1012.dto.BoardResDto;
import com.sbs.spring1012.entity.*;
import com.sbs.spring1012.repository.BoardRepository;
import com.sbs.spring1012.repository.CategoryRepository;
import com.sbs.spring1012.repository.GreateRepository;
import com.sbs.spring1012.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j //로그 메시지 출력
@Service  //스프링 빈 컨테이너에 등록
@RequiredArgsConstructor//생성자를 통해서 의존성 주입을 받기위한 어노테이션
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final GreateRepository greateRepository;

    //게시물 등록
    public boolean boardInsert(BoardReqDto boardReqDto,String email) {
        try {
            Board board = new Board();
            Member member = memberRepository.findByEmail(email).orElseThrow(
                            () -> new RuntimeException("존재하지않는 회원입니다."));
            Category category = categoryRepository.findByCategoryName(boardReqDto.getCategoryName()).orElseThrow(
                    ()-> new RuntimeException("존재하지않는 카테고리입니다."));

            board.setMember(member);
            board.setCategory(category);
            board.setTitle(boardReqDto.getTitle());
            board.setContent(boardReqDto.getContent());
            board.setImg(boardReqDto.getImg());

            System.out.println(board.getTitle());
            boardRepository.save(board);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
    //수정
    public boolean boardModify(BoardReqDto boardReqDto,Long id){
        try{
            //게시글 id를 받아와 수정할 게시글 불러오기
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("해당 게시글이 존재하지 않습니다."));
            System.out.println("게시글 찾음");

            Category category = categoryRepository.findByCategoryName(boardReqDto.getCategoryName()).orElseThrow(
                    () -> new RuntimeException("해당 카테고리가 존재하지 않습니다."));

            // 값이 null이 아니면 업데이트, null이면 기존 값 유지

            if (boardReqDto.getCategoryName() != null) {
                board.setCategory(category);
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

    //게시물 삭제
    public boolean boardDelete(Long id){
        try {
            Board board = boardRepository.findById(id).orElseThrow(
                    () -> new RuntimeException("게시물이 존재하지않습니다."));

            boardRepository.delete(board);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //게시물 상세 조회
    public BoardResDto boardDetail(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지않는 게시글입니다."));

        return BoardResDto.of(board);
    }

    //엔티티 -> dto
    private BoardResDto convertEntityToDto (Board board) {
        BoardResDto boardResDto = new BoardResDto();
        boardResDto.setId(board.getId());
        boardResDto.setMemberAlias(board.getMember().getAlias());
        boardResDto.setMemberImage(board.getMember().getImage());
        boardResDto.setCategoryName(board.getCategory().getCategoryName());
        boardResDto.setTitle(board.getTitle());
        boardResDto.setContent(board.getContent());
        boardResDto.setImg(board.getImg());
        boardResDto.setRegDate(board.getCreateDate());
        boardResDto.setGreatNum(board.getGreatNum());
        boardResDto.setViews(boardResDto.getViews());
        return boardResDto;
    }
    //게시글 전체 조회
    public List<BoardResDto> getBoardAll(){
        List<Board> boards = boardRepository.findAll();
        List<BoardResDto> boardList = new ArrayList<>();
        for(Board board : boards){
            boardList.add(BoardResDto.of(board));
        }
        return boardList;
    }

    //게시글 검색 리스트
    public List<BoardResDto> searchBoardList (String keyword,String categoryName,Pageable pageable){
        Page<Board> boardPage = boardRepository.findByKeywordAndCategoryName(keyword, categoryName, pageable);
        System.out.println("찾기 들어감");
        System.out.println("pageable:"+pageable);
        System.out.println("keyword:"+keyword);
        System.out.println("categoryName:"+categoryName);
        System.out.println(boardPage.getContent());
        //Page 객체를 dto로 변환하고
        //List타입으로 변환하여 반환
        return boardPage.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }
    //총 페이지 수 조회
    public int getTotalPage(int page,int size,String keyword,String categoryName){
        Pageable pageable = PageRequest.of(page,size);
        Page<Board> boards = boardRepository.findByKeywordAndCategoryName(keyword,categoryName,pageable);

        int totalPages = boards.getTotalPages();
//        if (totalPages > 0 && page >= totalPages) {
//
//            return totalPages - 1;
//        }

        return totalPages;
    }

    //게시글 페이지네이션
    public List<BoardResDto> getPageBoardList(int page,int size,String sort,String keyword,String categoryName){
        List<BoardResDto> boardList = new ArrayList<>();

        //정렬이 최신순(recent)이라면
        if(sort.equalsIgnoreCase("recent")){
            //날짜별로 내림차순하여 최신순으로 제목으로 오름차순한다.
            Pageable pageableRecent = PageRequest.of(page,size, Sort.by(Sort.Order.desc("createDate"),Sort.Order.asc("title")));
            boardList = this.searchBoardList(keyword,categoryName,pageableRecent);
        }
        //정렬이 과거순이라면(former)
        else if(sort.equalsIgnoreCase("former")){
            Pageable pageableFormer = PageRequest.of(page, size,Sort.by(Sort.Order.asc("createDate"),Sort.Order.asc("title")));
            boardList = this.searchBoardList(keyword,categoryName,pageableFormer);
        }
        return boardList;
    }
    //조회수
    public boolean viewCount(Long id){
        try{
            Board board = boardRepository.findById(id).orElseThrow(
                    ()-> new RuntimeException("존재하지않는 게시글 입니다.")
            );

            board.setViews((board.getViews())+1);
            boardRepository.save(board);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    //게시글 좋아요
    public boolean greatBoard(Long boardId, Long memberId){
        try {
            Board board = boardRepository.findById(boardId).orElseThrow(
                    () -> new RuntimeException("존재하지않는 게시글입니다.")
            );
            Member member = memberRepository.findById(memberId).orElseThrow(
                    () -> new RuntimeException("존재하지않는 회원입니다.")
            );

            Optional<Great> great= greateRepository.findByBoardAndMember(board,member);

            //좋아요가 눌려있다면 좋아요 삭제
            if(great.isPresent()){
                greateRepository.delete(great.get());
                board.setGreatNum(board.getGreatNum()-1); //좋아요 개수 감소
            }
            //안 눌려있다면 좋아요 추가
            else {
                Great newGreate = new Great();
                newGreate.setBoard(board);
                newGreate.setMember(member);

                board.setGreatNum(board.getGreatNum()+1); //좋아요 개수 증가
                greateRepository.save(newGreate);
            }
            boardRepository.save(board);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

