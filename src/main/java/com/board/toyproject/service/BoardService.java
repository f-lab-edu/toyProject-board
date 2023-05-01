package com.board.toyproject.service;

import com.board.toyproject.domain.Board;

import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;

public interface BoardService {

    Board writeBoard(Board board);

    Board findBoardByBoardId(int board_id);

    /*PagingResponseData<Board> findBoardByMemberId(String member_id);*/

    Pagination<Board> findBoardBySearchWord(PagingRequestData pagingRequestData);

    int updateBoard(Board board);

    int deleteBoard(Board board);
}
