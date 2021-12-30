package com.lee.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String index(){
        // /WEB-INF/views/index.jsp로 경로를 찾아간다.
        return "index";
    }

}
