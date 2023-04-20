package com.board.toyproject.service;

import com.board.toyproject.SpringConfig;
import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;


    @Test
    @DisplayName("게시물 저장하기 테스트")
    public void writeBoard(){
        //given
        Member member = new Member("test11", "유연");
        memberService.join(member);
        Board board = new Board(member.getMemberId(), "타이틀테스트");

        //when
        int save = boardService.writeBoard(board);

        //then
        assertThat(save).isEqualTo(1);
    }
}