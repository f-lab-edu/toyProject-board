package com.board.toyproject.controller;

import com.board.toyproject.controller.exception.BadRequestException;
import com.board.toyproject.domain.Member;
import com.board.toyproject.service.MemberService;
import com.board.toyproject.session.SessionConst;
import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor //final 생성자 자동 생성
public class SessionController {

    private final MemberService memberService;

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PostMapping("/login")
    public void login(@RequestBody @Valid Member member, BindingResult bindingResult,
            HttpServletRequest request)
            throws AuthenticationException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }
        Member loginMember = memberService.login(member);

        //로그인 성공 처리
        //세션이 있으면 있는 세션을 반환하고 , 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    }
}
