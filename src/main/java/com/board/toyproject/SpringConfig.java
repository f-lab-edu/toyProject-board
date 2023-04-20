package com.board.toyproject;

import com.board.toyproject.repository.BoardRepository;
import com.board.toyproject.repository.MemberRepository;
import com.board.toyproject.repository.MybatisMemberRepository;
import com.board.toyproject.service.BoardService;
import com.board.toyproject.service.BoardServiceImpl;
import com.board.toyproject.service.MemberService;
import com.board.toyproject.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, BoardRepository boardRepository){
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository);
    }
    @Bean
    public BoardService boardService(){
        return new BoardServiceImpl(boardRepository);
    }
}
