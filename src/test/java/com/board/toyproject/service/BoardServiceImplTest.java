package com.board.toyproject.service;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.Pagination;
import com.board.toyproject.domain.RequestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;


    @Test
    @DisplayName("게시물 저장하기 테스트")
    public void writeBoard() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");
        board.setContent("내용넣기");
        //when
        Board newBoard = boardService.writeBoard(board);

        //then
        assertThat(board.getBoardId()).isEqualTo(newBoard.getBoardId());
    }

    @Test
    @DisplayName("게시판 아이디로 게시물 찾기")
    public void findBoardByBoardId() {
        //given
        Member member = new Member("test11", "password123","유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");
        boardService.writeBoard(board);
        int boardId = board.getBoardId();
        //when
        Board getBoard = boardService.findBoardByBoardId(boardId);
        //then
        assertThat(getBoard.getBoardId()).isEqualTo(boardId);
    }

    @Test
    @DisplayName("게시판 아이디로 게시물 찾는데, 없을 때 오류")
    public void findBoardByBoardIdThrowException() {
        //given
        int missingNumber = -1;
        //when
        //then
        assertThrows(NoSuchElementException.class,
                () -> boardService.findBoardByBoardId(missingNumber));
    }

    @Test
    @DisplayName("멤버아이디로 게시물 리스트 찾기")
    public void findBoardByMemberId() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");
        boardService.writeBoard(board);
        Board board2 = new Board(member.getMemberId(), "타이틀테스트2");
        boardService.writeBoard(board2);
        Board board3 = new Board(member.getMemberId(), "타이틀테스트3");
        boardService.writeBoard(board3);

        RequestData requestData = new RequestData();
        requestData.setSearchType("MEMBER_ID");
        requestData.setSearchContent(member.getMemberId());

        //when
        Pagination<Board> responseData = boardService.findBoardBySearchWord(requestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(3);
    }

    @Test
    @DisplayName("제목으로 게시물들 찾기.")
    public void findBoardByTitle() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "배유연게시판");
        boardService.writeBoard(board);
        Board board2 = new Board(member.getMemberId(), "배유연게시판2");
        boardService.writeBoard(board2);
        Board board3 = new Board(member.getMemberId(), "이지후게시판");
        boardService.writeBoard(board3);

        //RequestData 생성
        RequestData requestData = new RequestData();
        requestData.setSearchType("TITLE");
        requestData.setSearchContent("배유연");

        //when
        Pagination<Board> responseData = boardService.findBoardBySearchWord(requestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(2);
    }
    @Test
    @DisplayName("검색타입으로 없는 타입 넣기")
    public void findBoardByNoType() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "배유연게시판");
        boardService.writeBoard(board);
        Board board2 = new Board(member.getMemberId(), "배유연게시판2");
        boardService.writeBoard(board2);
        Board board3 = new Board(member.getMemberId(), "이지후게시판");
        boardService.writeBoard(board3);

        //RequestData 생성
        RequestData requestData = new RequestData();
        requestData.setSearchType("NON_DATA");
        requestData.setSearchContent("배유연");

        //when
        //PagingResponseData<Board> responseData = boardService.findBoardBySearchWord(requestData);

        //then
        assertThrows(BadRequestException.class,
                () -> boardService.findBoardBySearchWord(requestData));
    }

    @Test
    @DisplayName("10건 페이징테스트")
    public void findAll() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);

        for(int i=0; i<20; i++){
            Board board = new Board(member.getMemberId(), "죠르디게시판"+i);
            boardService.writeBoard(board);
        }



        //RequestData 생성
        RequestData requestData = new RequestData();
        requestData.setRecordSize(10);

        //when
        Pagination<Board> responseData = boardService.findBoardBySearchWord(requestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("게시판 수정")
    public void updateBoard() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");
        board.setContent("내용before");

        boardService.writeBoard(board);
        //when
        board.setContent("내용after");
        boardService.updateBoard(board);
        Board afterBoard = boardService.findBoardByBoardId(board.getBoardId());
        //then
        assertThat(afterBoard.getContent()).isEqualTo("내용after");
    }

    @Test
    @DisplayName("게시판 삭제")
    public void deleteBoard() {
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");

        boardService.writeBoard(board);
        Board getBoard = boardService.findBoardByBoardId(board.getBoardId());
        assertThat(getBoard.getBoardId()).isEqualTo(board.getBoardId());
        //when
        boardService.deleteBoard(board);
        //then
        assertThrows(NoSuchElementException.class,
                () -> boardService.findBoardByBoardId(board.getBoardId()));
    }


}