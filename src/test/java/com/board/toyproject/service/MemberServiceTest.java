package com.board.toyproject.service;

import com.board.toyproject.SpringConfig;
import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.Pagination;
import com.board.toyproject.domain.RequestData;
import com.board.toyproject.repository.MemberRepository;
import lombok.Data;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //테스트를 시작할 때 트랜잭션 시작하고 테스트 끝나면 롤백해줌.
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입 테스트")
    void memberJoin() {
        //given
        Member member = new Member();
        member.setMemberId("testId123");
        member.setPassword("password123");
        member.setName("이름이름큐큐큐");
        member.setPhoneNum("1122233");
        //when
        memberService.join(member);
        //then
        Member findMember = memberService.findByMemberId(member.getMemberId()).get();

        assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
    }

    @Test
    @DisplayName("중복가입 테스트")
    void memberDuplicateJoin(){
        //given
        Member member = new Member("test11","password123", "유연");
        memberService.join(member);
        Member member2 = new Member("test11","password123", "유연중복");

        //when
        //memberService.join(member2);
        //then
        assertThrows(DuplicateKeyException.class, ()->  memberService.join(member2));

    }

    @Test
    @DisplayName("멤버10건 페이징 테스트")
    public void findAllMember(){
        for(int i=0; i<20; i++){
            Member member = new Member("memberIdTest"+i,"password123", "테스트 멤버명"+i);
            memberService.join(member);
        }
        //RequestData 생성
        RequestData requestData = new RequestData();

        //when
        Pagination<Member> responseData = memberService.findAllMember(requestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(10);
    }

    @Test
    void memberUpdate() {

        //given
        Member member = new Member();
        member.setMemberId("testId123");
        member.setName("이름이름큐큐큐");
        member.setPassword("password123");
        member.setPhoneNum("1122233");

        memberService.join(member); //일단 가입

        //when
        member.setName("이름수정합니다");
        memberService.updateMember(member);
        Member updateMember = memberService.findByMemberId("testId123").get();
        //then
        assertThat("이름수정합니다").isEqualTo(updateMember.getName());
    }

    @Test
    void memberDelete() {
        //given
        Member member = new Member();
        member.setMemberId("testId123");
        member.setName("이름이름큐큐큐");
        member.setPassword("paswword123");
        member.setPhoneNum("1122233");

        memberService.join(member); //일단 가입
        Member addMember = memberService.findByMemberId("testId123").get();
        assertThat("testId123").isEqualTo(addMember.getMemberId());
        //when
        String memberId = memberService.deleteMember(member);
        //then
        //Member removeMember = memberService.findByMemberId("testId123").get();
        //NoSuchElementException
        assertThrows(NoSuchElementException.class,
                () -> memberService.findByMemberId("testId123").get());
        //assertThat("이름수정합니다").isEqualTo(updateMember.getName());
    }
}