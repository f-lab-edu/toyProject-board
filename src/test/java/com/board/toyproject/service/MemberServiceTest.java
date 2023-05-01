package com.board.toyproject.service;

import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

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
    void memberDuplicateJoin() {
        //given
        Member member = new Member("test11", "유연");
        memberService.join(member);
        Member member2 = new Member("test11", "유연중복");

        //when
        //memberService.join(member2);
        //then
        assertThrows(DuplicateKeyException.class, () -> memberService.join(member2));

    }

    @Test
    @DisplayName("멤버10건 페이징 테스트")
    public void findAllMember() {
        for (int i = 0; i < 20; i++) {
            Member member = new Member("memberIdTest" + i, "테스트 멤버명" + i);
            memberService.join(member);
        }
        //PagingRequestData 생성
        PagingRequestData pagingRequestData = new PagingRequestData();

        //when
        Pagination<Member> responseData = memberService.findMemberBySearchWord(pagingRequestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(10);
    }

    @Test
    @DisplayName("멤버6건에 대한 페이징 테스트")
    public void find16Member() {
        final int record_size = 3;
        for (int i = 0; i < 6; i++) {
            Member member = new Member("memberIdTest" + (i + 1), "테스트 멤버명" + (i + 1));
            memberService.join(member);
        }
        //PagingRequestData 생성
        PagingRequestData pagingRequestData = new PagingRequestData();

        pagingRequestData.setSearchType("MEMBER_ID");
        pagingRequestData.setSearchContent("memberIdTest");
        pagingRequestData.setPage(1);
        pagingRequestData.setRecordSize(record_size);
        pagingRequestData.setOrderBy("MEMBER_ID");
        //when
        Pagination<Member> responseData = memberService.findMemberBySearchWord(pagingRequestData);
        //then
        assertThat(responseData.getList().size()).isEqualTo(3);

        for (int i = 0; i < responseData.getList().size(); i++) {
            Member getMember = responseData.getList().get(i);
            assertThat(getMember.getMemberId()).isEqualTo("memberIdTest" + (i + 1));
        }

        //given2

        pagingRequestData.setPage(2);
        pagingRequestData.setRecordSize(record_size);

        //when2
        Pagination<Member> responseData2 = memberService.findMemberBySearchWord(pagingRequestData);

        //then2
        assertThat(responseData2.getList().size()).isEqualTo(3);

        for (int i = 0; i < responseData2.getList().size(); i++) {
            Member getMember = responseData2.getList().get(i);
            assertThat(getMember.getMemberId()).isEqualTo("memberIdTest" + (i + 1 + record_size));
        }


    }

    @Test
    @DisplayName("멤버10건 페이징 테스트")
    public void findAllMember(){
        for(int i=0; i<20; i++){
            Member member = new Member("memberIdTest"+i, "테스트 멤버명"+i);
            memberService.join(member);
        }
        //PagingRequestData 생성
        PagingRequestData pagingRequestData = new PagingRequestData();

        //when
        Pagination<Member> responseData = memberService.findAllMember(pagingRequestData);

        //then
        assertThat(responseData.getList().size()).isEqualTo(10);
    }

    @Test
    void memberUpdate() {

        //given
        Member member = new Member();
        member.setMemberId("testId123");
        member.setName("이름이름큐큐큐");
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