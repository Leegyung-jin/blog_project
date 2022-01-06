package com.lee.blog.controller;

import com.lee.blog.config.auth.PrincipalDetail;
import com.lee.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // public String index(@AuthenticationPrincipal PrincipalDetail principal){  // 컨트롤러에서 어떻게 세션을 찾을까?
    @GetMapping({"", "/"})
    public String index(Model model){
        model.addAttribute("boards", boardService.글목록());

        // /WEB-INF/views/index.jsp로 경로를 찾아간다. frefix, suffix
        return "index"; // viewResolver가 작동한다.
    }

    // User권한이 필요하다
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
