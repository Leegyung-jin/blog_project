package com.lee.blog.controller;

import com.lee.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // public String index(@AuthenticationPrincipal PrincipalDetail principal){  // 컨트롤러에서 어떻게 세션을 찾을까?
    @GetMapping({"", "/"})
    public String index(){  // 컨트롤러에서 어떻게 세션을 찾을까?
        // /WEB-INF/views/index.jsp로 경로를 찾아간다.
        return "index";
    }

    // User권한이 필요하다
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
