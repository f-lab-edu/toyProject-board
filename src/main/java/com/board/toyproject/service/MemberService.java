package com.board.toyproject.service;

import com.board.toyproject.domain.Member;

import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import java.util.List;
import java.util.Optional;
import javax.security.sasl.AuthenticationException;

public interface MemberService {

    String join(Member member);

    Optional<Member> findByMemberId(String memberId);
    Member login(Member member) throws AuthenticationException;

    Pagination<Member> findMemberBySearchWord(PagingRequestData pagingRequestData);

    String updateMember(Member member);

    String deleteMember(Member member);

}
