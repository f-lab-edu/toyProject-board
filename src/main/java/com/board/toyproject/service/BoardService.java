package com.board.toyproject.service;

import com.board.toyproject.domain.Board;

import com.board.toyproject.domain.RequestDTO;
import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board writeBoard(Board board);

    Board findBoardByBoardId(int board_id);

    List<Board> findBoardByMemberId(String member_id);

    List<Board> findBoardBySearchWord(RequestDTO requestDTO);

    List<Board> findAll();

    int updateBoard(Board board);

    int deleteBoard(Board board);
}
