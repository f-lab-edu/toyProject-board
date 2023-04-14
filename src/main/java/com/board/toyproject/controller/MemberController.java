package com.board.toyproject.controller;

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
public class MemberController {

    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //--------------------예외처리를 다룹니다.-------------------------//


    //404 error 처리
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
    //409 error 처리
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleDupException(DuplicateKeyException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
    //500 error 처리
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleAllException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }


    //---------------------------------------------------------//
    /**
     * 회원가입
     * @param member
     * @return
     */
    @RequestMapping(value = "/members", method = {RequestMethod.POST})
    @ResponseBody
    public Member createMember(@RequestBody Member member) {

        if(memberService.chkDuplicateMemberId(member)){ //ID로 중복 체크
            throw new DuplicateKeyException("이미 가입한 회원입니다.");
        }
        memberService.join(member);
        Member result = memberService.findByMemberId(member.getMemberId()).get();
        return result;
    }

    /**
     * 멤버 찾기
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/members", method = {RequestMethod.GET})
    @ResponseBody
    public List<Member> requestMemberById(@RequestParam(value = "memberId",required = false) String memberId
                                    ,@RequestParam(value = "name",required = false) String name){
        List<Member> members =new ArrayList<>();
        if(memberId !=null){
            Member member = memberService.findByMemberId(memberId).orElse(null);
            members.add(member);
        }else if(name !=null){

            members = memberService.findByMemberName(name);
        }else{
            members = memberService.findAllMember();
        }

        return members;
    }


    /**
     * 멤버 정보 수정
     * @param member
     * @return
     */
    @RequestMapping(value = "/members", method = {RequestMethod.PUT})
    @ResponseBody
    public Member updateMember(@RequestBody Member member) {
        //Map<String, Object> resultMap = new HashMap<>();
        if (member == null) {
            throw new NoSuchElementException("수정하려는 멤버정보를 입력해주세요.");
        }else{
            if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
                throw new NoSuchElementException("수정하려는 아이디가 존재하지 않습니다.");
            }

            String memberId=memberService.updateMember(member);
            Member result = memberService.findByMemberId(memberId).get();
            return result;
        }
    }
    /**
     * 멤버 탈퇴
     * @param member
     * @return
     */
    @RequestMapping(value = "/members", method = {RequestMethod.DELETE})
    @ResponseBody
    public String deleteMember(@RequestBody Member member) {
        if (member == null) {
            throw new NoSuchElementException("수정하려는 멤버정보를 입력해주세요.");
        }
        if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
            throw new NoSuchElementException("수정하려는 아이디가 존재하지 않습니다.");
        }
        String memberId=memberService.deleteMember(member);

        return "delete member";
    }
}
