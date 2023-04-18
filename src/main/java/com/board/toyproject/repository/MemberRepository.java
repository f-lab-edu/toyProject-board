package com.board.toyproject.repository;

import com.board.toyproject.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    int saveMember(Member member);

    Optional<Member> findById(String memberId);

    List<Member> findByName(String name);

    List<Member> findAll();

    int deleteMember(Member member);

    int updateMember(Member member);

}
