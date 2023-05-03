package com.board.toyproject.repository;

import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.paging.PagingRequestData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisMemberRepository extends MemberRepository {

    @Override
    int saveMember(Member member);

    @Override
    Optional<Member> findById(String memberId);

    @Override
    List<Member> findByName(String name);

    @Override
    List<Member> findAll(PagingRequestData pagingRequestData);

    @Override
    int memberCount(PagingRequestData pagingRequestData);

    @Override
    int deleteMember(Member member);

    @Override
    int updateMember(Member member);
}
