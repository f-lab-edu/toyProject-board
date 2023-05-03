package com.board.toyproject.controller;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Member;
import com.board.toyproject.domain.paging.Pagination;
import com.board.toyproject.domain.paging.PagingRequestData;
import com.board.toyproject.service.MemberService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    /**
     * 회원가입
     *
     * @param member
     * @return
     */

    @PostMapping
    public Member createMember(@RequestBody @Valid Member member, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        memberService.join(member);
        Member result = memberService.findByMemberId(member.getMemberId()).get();
        return result;
    }

    /**
     * 모든 멤버 찾기
     *
     * @return
     */
    @GetMapping
    public Pagination<Member> requestMemberAll(@RequestBody PagingRequestData pagingRequestData) {
        return memberService.findAllMember(pagingRequestData);
    }

    /**
     * 이름으로 멤버 찾기
     *
     * @param name
     * @return
     */
    @GetMapping("/name/{name}")
    public List<Member> requestMemberByName(@PathVariable String name) {
        List<Member> members = memberService.findByMemberName(name);
        if (members.size() == 0) {
            throw new NoSuchElementException("이름이 '" + name + "' 멤버는 존재하지 않습니다.");
        }
        return members;
    }

    /**
     * 아이디로 멤버 찾기
     *
     * @param memberId
     * @return
     */
    @GetMapping("/memberId/{memberId}")
    public List<Member> requestMemberById(@PathVariable String memberId) {
        List<Member> members = new ArrayList<>();
        Member member = memberService.findByMemberId(memberId).orElse(null);
        if (member == null) {
            throw new NoSuchElementException("아이디가 '" + memberId + "' 멤버는 존재하지 않습니다.");
        }
        members.add(member);
        return members;
    }


    /**
     * 멤버 정보 수정
     *
     * @param member
     * @return
     */
    @PatchMapping
    public Member updateMember(@RequestBody(required = true) Member member) {

        if (!memberService.findByMemberId(member.getMemberId()).isPresent()) {
            throw new NoSuchElementException("수정하려는 아이디가 존재하지 않습니다.");
        }

        String memberId = memberService.updateMember(member);
        Member result = memberService.findByMemberId(memberId).get();
        return result;

    }

    /**
     * 멤버 탈퇴
     *
     * @param member
     * @return
     */
    @DeleteMapping
    public String deleteMember(@RequestBody(required = true) Member member) {

        if (!memberService.findByMemberId(member.getMemberId()).isPresent()) {
            throw new NoSuchElementException("삭제하려는 아이디가 존재하지 않습니다.");
        }
        String memberId = memberService.deleteMember(member);
        return "delete member";
    }
}
