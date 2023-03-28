package com.board.toyproject.controller;

import com.board.toyproject.domain.Member;
import com.board.toyproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MemberController {


    private final MemberService memberService;
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/create_member", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> createUser(@RequestBody Member member) {
        System.out.println("member = " + member);
        memberService.join(member);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", "added");
        return resultMap;
    }
}
