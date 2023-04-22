package com.board.toyproject.service;

import com.board.toyproject.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    String join(Member member);

    Optional<Member> findByMemberId(String memberId);

    List<Member> findByMemberName(String name);

    List<Member> findAllMember();

    String updateMember(Member member);

    String deleteMember(Member member);

}
