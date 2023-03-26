package com.board.toyproject.repository;

import com.board.toyproject.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MybatisBoardRepository extends BoardRepository{
    public List<Board>  getListBoard();
}
