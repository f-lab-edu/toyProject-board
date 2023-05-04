package com.board.toyproject.repository;

import com.board.toyproject.domain.Member;

import com.board.toyproject.domain.paging.PagingRequestData;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    int saveMember(Member member);

    Optional<Member> findById(String memberId);

    List<Member> findMemberBySearchWord(PagingRequestData pagingRequestData);

    int memberCount(PagingRequestData pagingRequestData);

    int deleteMember(Member member);

    int updateMember(Member member);

}
