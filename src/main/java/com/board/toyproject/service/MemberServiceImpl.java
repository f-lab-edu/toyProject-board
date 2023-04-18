package com.board.toyproject.service;

import com.board.toyproject.domain.Member;
import com.board.toyproject.repository.MemberRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String join(Member member) {

        memberRepository.saveMember(member);
        return member.getMemberId();
    }

    /**
     * id로 멤버 검색
     *
     * @param memberId
     * @return
     */
    public Optional<Member> findByMemberId(String memberId){
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

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    //멤버 수정
    public String updateMember(Member member) {

        memberRepository.updateMember(member);
        return member.getMemberId();
    }
    //멤버 탈퇴
    public String deleteMember(Member member){
        memberRepository.deleteMember(member);
        return member.getMemberId();
    }
    public boolean isDuplicatedMemberId(Member member){
        if(memberRepository.findById(member.getMemberId()).isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
