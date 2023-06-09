package com.board.toyproject.controller;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import com.board.toyproject.domain.paging.PagingRequestType;
import com.board.toyproject.service.BoardService;
import com.board.toyproject.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public Board createBoard(@RequestBody @Valid Board board, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
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
     * 모든 게시판 조회(검색타입, 검색어로 조회 가능)
     *
     * @return
     */
    @GetMapping
    public Pagination<Board> requestBoardAll(@RequestBody PagingRequestData pagingRequestData) {
        if (pagingRequestData.getSearchType() !=null && !"".equals(pagingRequestData.getSearchType())
                && pagingRequestData.getSearchContent() !=null && !"".equals(pagingRequestData.getSearchContent())
                  && pagingRequestData.getSearchType().equals(PagingRequestType.MEMBER_ID.toString())) {

            String memberId = pagingRequestData.getSearchContent();
            Member member = memberService.findByMemberId(memberId).orElse(null);
            if (member == null) {
                throw new NoSuchElementException("아이디가 '" + memberId + "' 멤버는 존재하지 않습니다.");
            }
        }
        return boardService.findBoardBySearchWord(pagingRequestData);
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
