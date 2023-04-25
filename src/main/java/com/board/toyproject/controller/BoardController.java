package com.board.toyproject.controller;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.RequestDTO;
import com.board.toyproject.service.BoardService;
import com.board.toyproject.service.MemberService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor // final 생성자 자동 생성
@RequestMapping(value = "/v1/boards")
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    /**
     * 게시판 작성
     *
     * @param board
     * @return
     */
    @PostMapping
    public Board createBoard(@RequestBody Board board) {
        boardService.writeBoard(board);
        return board;
    }

    /**
     * 게시판 아이디로 게시물 조회
     *
     * @param boardId
     * @return
     */
    @GetMapping("/boardId/{boardId}")
    public Board requestBoardByBoardId(@PathVariable int boardId) {
        return boardService.findBoardByBoardId(boardId);
    }

    /**
     * 회원아이디로 게시물 조회
     *
     * @param memberId
     * @return
     */
    @GetMapping("/memberId/{memberId}")
    public PageInfo<Board> requestBoardByMemberId(@PathVariable String memberId,
            RequestDTO requestDTO) {

        Member member = memberService.findByMemberId(memberId).orElse(null);
        if (member == null) {
            throw new NoSuchElementException("아이디가 '" + memberId + "' 멤버는 존재하지 않습니다.");
        }
        /*List<Board> boards = boardService.findBoardByMemberId(memberId);

        if (boards.size() == 0) {
            throw new NoSuchElementException(memberId + "님이 작성하신 게시판이 존재하지 않습니다.");
        }

        return boards;*/
        //페이징 처리 세팅
        PageHelper.startPage(requestDTO);

        return PageInfo.of(boardService.findBoardByMemberId(memberId));
    }

    /**
     * 게시판 제목으로 게시물 조회
     *
     * @param
     * @return
     */
    /*@GetMapping("/detail")
    public PageInfo<Board> requestBoardDetail(RequestDTO requestDTO) {
        //List<Board> boards = boardService.findBoardByTitle(title);
        PageHelper.startPage(requestDTO);

        return PageInfo.of(boardService.findBoardBySearchWord(requestDTO));
    }*/

    /**
     * 모든 게시판 조회(검색타입, 검색어로 조회 가능)
     *
     * @return
     */
    @GetMapping()
    public PageInfo<Board> requestBoardAll(RequestDTO requestDTO) {

        PageHelper.startPage(requestDTO);

        /*List<Board> boards = (List<Board>) PageInfo.of(boardService.findAll(requestDTO));
        if (boards.size() == 0) {
            throw new NoSuchElementException("게시판이 존재하지 않습니다.");
        }
        return boards;*/
        return PageInfo.of(boardService.findBoardBySearchWord(requestDTO));
    }

    /**
     * 게시판 수정
     *
     * @param board
     * @return
     */
    @PatchMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void updateBoard(@RequestBody(required = true) Board board) {
        boardService.updateBoard(board);
    }

    /**
     * 게시판 삭제
     *
     * @param board
     * @return
     */
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteBoard(@RequestBody(required = true) Board board) {
        boardService.deleteBoard(board);
    }
}
