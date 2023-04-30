package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.RequestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisBoardRepository extends BoardRepository {


    @Override
    int saveBoard(Board board);

    @Override
    Optional<Board> findBoardByBoardId(int board_id);

    /*@Override
    List<Board> findBoardByMemberId(String member_id);*/

    @Override
    List<Board> findBoardBySearchWord(RequestData requestData);
    //List<Board> findBoardByTitle(String title);

    int count(RequestData requestData);

    @Override
    int deleteBoard(Board board);

    @Override
    int updateBoard(Board board);
}
