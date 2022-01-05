package com.lee.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증되지 않은 사용자가 출입할 수 있는 경로 /auth
// 그냥 주소가 /일 때 index.jsp로 허용
// static 이하에 있는 resource 파일(js/**, css/**, image/**) 허용

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }
}
