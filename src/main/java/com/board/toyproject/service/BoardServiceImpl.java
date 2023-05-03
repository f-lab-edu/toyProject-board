package com.board.toyproject.service;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import com.board.toyproject.domain.paging.PagingRequestType;
import com.board.toyproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor //final 생성자 자동 생성
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Board writeBoard(Board board) {
        boardRepository.saveBoard(board);
        return board;
    }

    @Override
    public Board findBoardByBoardId(int board_id) {
        return boardRepository.findBoardByBoardId(board_id).orElseThrow(() ->
                new NoSuchElementException("게시판 아이디가 '" + board_id + "'인 게시물을 찾을 수 없습니다."));
    }

    /*@Override
    public PagingResponseData<Board> findBoardByMemberId(String member_id) {
        return boardRepository.findBoardByMemberId(member_id);
    }*/

    @Override
    public Pagination<Board> findBoardBySearchWord(PagingRequestData pagingRequestData) {
        if (pagingRequestData.getSearchType() != null && pagingRequestData.getSearchType() != "") {
            if (PagingRequestType.isRequestType(pagingRequestData.getSearchType()) == false) { //올바른 요청 조건이 아니면

                throw new BadRequestException("요청하신 검색조건은 지원하지 않습니다.");
            }
        }

        int count = boardRepository.count(pagingRequestData);
        Pagination<Board> pagination = new Pagination(count, pagingRequestData);
        pagingRequestData.setPagination(pagination);
        List<Board> list = boardRepository.findBoardBySearchWord(pagingRequestData);
        pagination.setList(list);
        //return new PagingResponseData<>(list, pagination);
        return pagination;
    }


    @Override
    public int updateBoard(Board board) {
        return boardRepository.updateBoard(board);
    }

    @Override
    public int deleteBoard(Board board) {
        return boardRepository.deleteBoard(board);
    }
}
