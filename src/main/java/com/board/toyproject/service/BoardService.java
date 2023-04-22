package com.board.toyproject.service;

import com.board.toyproject.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    int writeBoard(Board board);

    Optional<Board> findBoardByBoardId(int board_id);

    List<Board> findBoardByMemberId(String member_id);

    List<Board> findBoardByTitle(String title);

    List<Board> findAll();

    int updateBoard(Board board);

    int deleteBoard(Board board);
}
