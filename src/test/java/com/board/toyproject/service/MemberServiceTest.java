package com.board.toyproject.service;

import com.board.toyproject.domain.Member;
import com.board.toyproject.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional //테스트를 시작할 때 트랜잭션 시작하고 테스트 끝나면 롤백해줌.
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setMemberId("testId");
        member.setName("name");
        member.setPhoneNum("1122233");
        //when
        memberService.join(member);
        //then
        Member findMember = memberService.findByMemberId(member.getMemberId()).get();
        Assertions.assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());

    }
    @Test
    public void test(){
        System.out.println("pull request test3");
    }
}