package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.RequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisBoardRepository extends BoardRepository {


    @Override
    int saveBoard(Board board);

    @Override
    Optional<Board> findBoardByBoardId(int board_id);

    @Override
    List<Board> findBoardByMemberId(String member_id);

    @Override
    List<Board> findBoardBySearchWord(RequestDTO requestDTO);
    //List<Board> findBoardByTitle(String title);

    @Override
    List<Board> findAll();

    @Override
    int deleteBoard(Board board);

    @Override
    int updateBoard(Board board);
}
