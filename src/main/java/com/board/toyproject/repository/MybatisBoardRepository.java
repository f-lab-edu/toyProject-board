package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisBoardRepository extends BoardRepository{
    @Override
    int saveBoard(Board board);

    @Override
    Optional<Board> findBoardByBoardId();

    @Override
    List<Board> findBoardByMemberId();

    @Override
    List<Board> findBoardByTitle();

    @Override
    List<Board> findAll();

    @Override
    int deleteBoard(Board board);

    @Override
    int updateBoard(Board board);

}
