package com.board.toyproject.service;

import com.board.toyproject.domain.Board;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.Pagination;
import com.board.toyproject.domain.RequestData;
import com.board.toyproject.repository.MemberRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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

    /**
     * 이름으로 멤버 검색
     *
     * @param name
     * @return
     */
    public List<Member> findByMemberName(String name) {
        return memberRepository.findByName(name);
    }

    public Pagination<Member> findAllMember(RequestData requestData) {

        int count = memberRepository.memberCount(requestData);
        Pagination<Member> pagination = new Pagination(count, requestData);
        requestData.setPagination(pagination);
        List<Member> list = memberRepository.findAll(requestData);
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
