package com.board.toyproject.controller;

import com.board.toyproject.domain.Member;
import com.board.toyproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
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
    @RequestMapping(value = "/create_member", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> createMember(@RequestBody Member member) {
        Map<String, Object> resultMap = new HashMap<>();
        if(memberService.chkDupMemberId(member)){ //ID로 중복 체크
            resultMap.put("result", "already joined");
            return resultMap;
        }
        resultMap.put("result", "added");
        memberService.join(member);
        return resultMap;
    }

    /**
     * 아이디로 멤버 찾기
     * @param memberId
     * @return
     */
    @GetMapping("/requestMemberById")
    @ResponseBody
    public Member requestMemberById(@RequestParam(value = "memberId",required = false) String memberId){
        Member member = memberService.findByMemberId(memberId).orElse(null);

        return member;
    }

    /**
     * 이름으로 멤버 찾기
     * @param name
     * @return
     */
    @GetMapping("/requestMemberByName")
    @ResponseBody
    public Member requestMemberByName(@RequestParam(value = "name",required = false) String name){
        Member member = memberService.findByMemberName(name).orElse(null);

        return member;
    }

    /**
     * 모든 멤버 조회하기
     * @return
     */
    @GetMapping("/requestMemberAll")
    @ResponseBody
    public List<Member> requestUserAll(){
        List<Member> memberList = memberService.findAllMember();
        return memberList;
    }

    /**
     * 멤버 정보 수정
     * @param member
     * @return
     */
    @RequestMapping(value = "/updateMember", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> updateMember(@RequestBody Member member) {
        Map<String, Object> resultMap = new HashMap<>();
        if (member == null) {
            resultMap.put("result", "멤버 정보를 입력하세요.");
        }else{
            if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
                resultMap.put("result", "수정하려는 아이디가 존재하지 않습니다.");
            }

            String memberId=memberService.updateMember(member);
            resultMap.put("result", memberId+" updated");
        }
        return resultMap;
    }
    /**
     * 멤버 탈퇴
     * @param member
     * @return
     */
    @RequestMapping(value = "/deleteMember", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> deleteMember(@RequestBody Member member) {
        Map<String, Object> resultMap = new HashMap<>();

        if (member == null) {
            resultMap.put("result", "멤버 정보를 입력하세요.");
        }
        if(!memberService.findByMemberId(member.getMemberId()).isPresent()){
            resultMap.put("result", "삭제하려는 아이디가 존재하지 않습니다.");
        }
        String memberId=memberService.deleteMember(member);
        resultMap.put("result", memberId+" deleted");
        return resultMap;
    }
}
