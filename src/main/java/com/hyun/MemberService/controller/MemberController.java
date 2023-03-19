package com.hyun.MemberService.controller;

import com.hyun.MemberService.Dto.MemberDto;
import com.hyun.MemberService.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 컨트롤러 : 웹 요청을 처리하는 컴포넌트
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    @GetMapping("/Register") // "/Register"이 요청(Get)되면 아래 함수 실행
    public String RegisterForm() {
        return "Register"; // templates 폴더의 Register.html을 실행
    }

    @PostMapping("/Register") // "/Register"이 요청(Post)되면 아래 함수 실행
    public String Register(/*@RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("name") String name*/
                       @ModelAttribute MemberDto memberDTO) {
        memberService.Register(memberDTO);
        return "login"; // templates 폴더의 login.html을 실행
    }

    @GetMapping("/login") // "/login"이 요청(Get)되면 아래 함수 실행
    public String loginForm() {
        return "Register"; // templates 폴더의 login.html을 실행
    }

    @PostMapping("/login") // "/login"이 요청(Post)되면 아래 함수 실행
    public String login(@ModelAttribute MemberDto memberDTO) {
        MemberDto login = memberService.login(memberDTO);
        if (login != null) {
            return "main"; // templates 폴더의 main.html을 실행
        } else {
            return "login"; // templates 폴더의 login.html을 실행
        }
    }
}
