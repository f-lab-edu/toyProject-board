package com.board.toyproject.controller;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Member;
import com.board.toyproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.*;

@RestController
@RequestMapping(value = "/board/v1/member")
public class MemberController {

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 회원가입
     * @param member
     * @return
     */

    @PostMapping
    @ResponseBody
    public Member createMember(@RequestBody Member member) {

        if(memberService.isDuplicatedMemberId(member)){ //ID로 중복 체크
            throw new DuplicateKeyException("이미 가입한 회원입니다.");
        }
        memberService.join(member);
        Member result = memberService.findByMemberId(member.getMemberId()).get();
        return result;
    }
    /**
     * 모든 멤버 찾기
     * @return
     */
    @GetMapping
    //@PostMapping
    @ResponseBody
    public List<Member> requestMemberAll(){
        List<Member> members = memberService.findAllMember();
        return members;
    }
    /**
     * 이름으로 멤버 찾기
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    //@PostMapping
    @ResponseBody
    public List<Member> requestMemberByName(@PathVariable String name){
        List<Member> members = memberService.findByMemberName(name);
        if(members.size()==0){
            throw new NoSuchElementException("이름이 '"+name+"' 멤버는 존재하지 않습니다.");
        }
        return members;
    }

    /**
     * 아이디로 멤버 찾기
     * @param memberId
     * @return
     */
    @GetMapping("/memberId/{memberId}")
    //@PostMapping
    @ResponseBody
    public List<Member> requestMemberById(@PathVariable String memberId){
        List<Member> members = new ArrayList<>();
        Member member = memberService.findByMemberId(memberId).orElse(null);
        if(member==null){
            throw new NoSuchElementException("아이디가 '"+memberId+"' 멤버는 존재하지 않습니다.");
        }
        members.add(member);
        return members;
    }


    /**
     * 멤버 정보 수정
     * @param member
     * @return
     */
    @PutMapping
    @ResponseBody
    public Member updateMember(@RequestBody (required=true) Member member) {

        if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
            throw new NoSuchElementException("수정하려는 아이디가 존재하지 않습니다.");
        }

        String memberId = memberService.updateMember(member);
        Member result = memberService.findByMemberId(memberId).get();
        return result;

    }
    /**
     * 멤버 탈퇴
     * @param member
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public String deleteMember(@RequestBody (required=true) Member member) {

        if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
            throw new NoSuchElementException("삭제하려는 아이디가 존재하지 않습니다.");
        }
        String memberId = memberService.deleteMember(member);
        return "delete member";
    }
}
