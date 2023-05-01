package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.paging.PagingRequestData;
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
    List<Board> findBoardBySearchWord(PagingRequestData pagingRequestData);
    //List<Board> findBoardByTitle(String title);

    int count(PagingRequestData pagingRequestData);

    @Override
    int deleteBoard(Board board);

    @Override
    int updateBoard(Board board);
}
