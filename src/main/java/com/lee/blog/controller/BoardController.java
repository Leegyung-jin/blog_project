package com.lee.blog.controller;

import com.lee.blog.config.auth.PrincipalDetail;
import com.lee.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // public String index(@AuthenticationPrincipal PrincipalDetail principal){  // 컨트롤러에서 어떻게 세션을 찾을까?
    @GetMapping({"", "/"})
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC) Pageable pageable){
        model.addAttribute("boards", boardService.글목록(pageable));

        // /WEB-INF/views/index.jsp로 경로를 찾아간다. frefix, suffix
        return "index"; // viewResolver가 작동한다.
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }

    // User권한이 필요하다
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
