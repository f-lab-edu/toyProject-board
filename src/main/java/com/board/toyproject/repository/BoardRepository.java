package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;

import com.board.toyproject.domain.paging.PagingRequestData;
import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    int saveBoard(Board board);

    Optional<Board> findBoardByBoardId(int board_id);

    //List<Board> findBoardByMemberId(String member_id);

    List<Board> findBoardBySearchWord(PagingRequestData pagingRequestData);


    int count(PagingRequestData pagingRequestData);
    int deleteBoard(Board board);

    int updateBoard(Board board);

}
