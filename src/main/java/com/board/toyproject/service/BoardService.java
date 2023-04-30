package com.board.toyproject.service;

import com.board.toyproject.domain.Board;

import com.board.toyproject.domain.Pagination;
import com.board.toyproject.domain.RequestData;

public interface BoardService {

    Board writeBoard(Board board);

    Board findBoardByBoardId(int board_id);

    /*PagingResponseData<Board> findBoardByMemberId(String member_id);*/

    Pagination<Board> findBoardBySearchWord(RequestData requestData);

    int updateBoard(Board board);

    int deleteBoard(Board board);
}
