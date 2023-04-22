package com.board.toyproject.controller;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.service.BoardService;
import com.board.toyproject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Board result = boardService.findBoardByBoardId(board.getBoardId()).get();
        return result;
    }

    /**
     * 게시판 아이디로 게시물 조회
     *
     * @param boardId
     * @return
     */
    @GetMapping("/boardId/{boardId}")
    public Board requestBoardByBoardId(@PathVariable int boardId) {
        return boardService
                .findBoardByBoardId(boardId)
                .orElseThrow(() -> new NoSuchElementException(
                        "게시판 아이디가 " + boardId + "인 게시물을 찾을 수 없습니다."));
    }

    /**
     * 회원아이디로 게시물 조회
     *
     * @param memberId
     * @return
     */
    @GetMapping("/memberId/{memberId}")
    public List<Board> requestBoardByMemberId(@PathVariable String memberId) {

        Member member = memberService.findByMemberId(memberId).orElse(null);
        if (member == null) {
            throw new NoSuchElementException("아이디가 '" + memberId + "' 멤버는 존재하지 않습니다.");
        }
        List<Board> boards = boardService.findBoardByMemberId(memberId);

        if (boards.size() == 0) {
            throw new NoSuchElementException(memberId + "님이 작성하신 게시판이 존재하지 않습니다.");
        }

        return boards;
    }

    /**
     * 게시판 제목으로 게시물 조회
     *
     * @param title
     * @return
     */
    @GetMapping("/title/{title}")
    public List<Board> requestBoardByTitle(@PathVariable String title) {
        List<Board> boards = boardService.findBoardByTitle(title);
        if (boards.size() == 0) {
            throw new NoSuchElementException("게시판 제목이 '" + title + "'인 게시판이 존재하지 않습니다.");
        }
        return boards;
    }

    /**
     * 모든 게시판 조회
     *
     * @return
     */
    @GetMapping()
    public List<Board> requestBoardAll() {
        List<Board> boards = boardService.findAll();
        if (boards.size() == 0) {
            throw new NoSuchElementException("게시판이 존재하지 않습니다.");
        }
        return boards;
    }

    /**
     * 게시판 수정
     *
     * @param board
     * @return
     */
    @PatchMapping
    public Board updateBoard(@RequestBody(required = true) Board board) {

        if (!boardService.findBoardByBoardId(board.getBoardId()).isPresent()) {
            throw new NoSuchElementException("수정하려는 게시판 아이디가 존재하지 않습니다.");
        }

        boardService.updateBoard(board);

        return boardService.findBoardByBoardId(board.getBoardId()).get();
    }

    /**
     * 게시판 삭제
     *
     * @param board
     * @return
     */
    @DeleteMapping
    public String deleteBoard(@RequestBody(required = true) Board board) {

        if (!boardService.findBoardByBoardId(board.getBoardId()).isPresent()) {
            throw new NoSuchElementException("수정하려는 게시판 아이디가 존재하지 않습니다.");
        }
        boardService.deleteBoard(board);
        return "delete board";
    }
}
