package com.board.toyproject.service;

import com.board.toyproject.domain.Member;

import com.board.toyproject.domain.Pagination;
import com.board.toyproject.domain.RequestData;
import java.util.List;
import java.util.Optional;
import javax.security.sasl.AuthenticationException;

public interface MemberService {

    String join(Member member);

    Optional<Member> findByMemberId(String memberId);

    Member login(Member member) throws AuthenticationException;

    List<Member> findByMemberName(String name);

    Pagination<Member> findAllMember(RequestData requestData);

    String updateMember(Member member);

    String deleteMember(Member member);

}
