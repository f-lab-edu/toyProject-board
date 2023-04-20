package com.board.toyproject.service;

import com.board.toyproject.domain.Board;
import com.board.toyproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor //final 생성자 자동 생성
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public int writeBoard(Board board) {
        int saveData = boardRepository.saveBoard(board);
        return saveData;
    }

    @Override
    public Optional<Board> findBoardByBoardId(String board_id) {
        return Optional.of(boardRepository.findBoardByBoardId(board_id).orElseThrow(()->
                    new NoSuchElementException("해당하는 게시물을 찾을 수 없습니다.")));
    }

    @Override
    public List<Board> findBoardByMemberId(String member_id) {
        return boardRepository.findBoardByMemberId(member_id);
    }

    @Override
    public List<Board> findBoardByTitle(String title) {
        return boardRepository.findBoardByTitle(title);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public int updateMember(Board board) {
        return boardRepository.updateBoard(board);
    }

    @Override
    public int deleteMember(Board board) {
        return boardRepository.deleteBoard(board);
    }
}
