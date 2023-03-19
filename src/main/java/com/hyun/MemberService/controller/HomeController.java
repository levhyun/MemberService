package com.hyun.MemberService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 컨트롤러
public class HomeController {

    @GetMapping("/") // "/"이 요청되면 아래 함수 실행
    public String index() {
        return "index"; // templates 폴더의 index.html을 실행
    }
}
