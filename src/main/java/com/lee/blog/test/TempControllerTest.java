package com.lee.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

    // http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome()");
        // 파일을 return할 때의 기본 경로: src/main/resources/static
        // 리턴명: /home.html이라고 작성해야 경로를 찾을 수 있다.
        // 전체 경로: src/main/resources/static/home.html
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg() {
        return "/a.png";
    }

    @GetMapping("/temp/jsp")
    public String tempJsp() {
        // prefix: /WEB-INF/views/
        // suffix: .jsp
        // 전체경로: /WEB-INF/views//test.jsp.jsp
        return "test";
    }
}
