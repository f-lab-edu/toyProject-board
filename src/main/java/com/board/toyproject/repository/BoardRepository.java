package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    int saveBoard(Board board);

    Optional<Board> findBoardByBoardId();

    List<Board> findBoardByMemberId();

    List<Board> findBoardByTitle();

    List<Board> findAll();

    int deleteBoard(Board board);

    int updateBoard(Board board);

}
