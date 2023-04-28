package com.board.toyproject.service;

import com.board.toyproject.domain.Board;

import com.board.toyproject.domain.PagingResponseData;
import com.board.toyproject.domain.RequestData;
import java.util.List;

public interface BoardService {

    Board writeBoard(Board board);

    Board findBoardByBoardId(int board_id);

    /*PagingResponseData<Board> findBoardByMemberId(String member_id);*/

    PagingResponseData<Board> findBoardBySearchWord(RequestData requestData);

    int updateBoard(Board board);

    int deleteBoard(Board board);
}
