package com.board.toyproject.service;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import com.board.toyproject.domain.paging.PagingRequestType;
import com.board.toyproject.repository.MemberRepository;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(Member member) {
        if (isDuplicatedMemberId(member)) { //ID로 중복 체크
            throw new DuplicateKeyException("이미 가입한 회원입니다.");
        }
        memberRepository.saveMember(member);
        return member.getMemberId();
    }

    /**
     * id로 멤버 검색
     *
     * @param memberId
     * @return
     */
    public Optional<Member> findByMemberId(String memberId) {
        return Optional.of(memberRepository.findById(memberId).orElseThrow(() ->
                new NoSuchElementException("해당하는 member를 찾을 수 없습니다.")));
    }

    public Pagination<Member> findMemberBySearchWord(PagingRequestData pagingRequestData) {
        if (pagingRequestData.getSearchType() != null && pagingRequestData.getSearchType() != "") {
            if (PagingRequestType.isRequestType(pagingRequestData.getSearchType()) == false) { //올바른 요청 조건이 아니면

                throw new BadRequestException("요청하신 검색조건은 지원하지 않습니다.");
            }
        }
        int count = memberRepository.memberCount(pagingRequestData);
        Pagination<Member> pagination = new Pagination(count, pagingRequestData);
        pagingRequestData.setPagination(pagination);
        List<Member> list = memberRepository.findMemberBySearchWord(pagingRequestData);
        pagination.setList(list);
        return pagination;
    }

    //멤버 수정
    public String updateMember(Member member) {

        memberRepository.updateMember(member);
        return member.getMemberId();
    }

    //멤버 탈퇴
    public String deleteMember(Member member) {
        memberRepository.deleteMember(member);
        return member.getMemberId();
    }

    private boolean isDuplicatedMemberId(Member member) {
        return memberRepository.findById(member.getMemberId()).isPresent();
    }
}
