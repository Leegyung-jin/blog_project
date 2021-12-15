package com.lee.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 스프링이 com.lee.blog 패키지 이하에서
// 특정 어노테이션이 붙어있는 클래스 파일들을 new해서 (IoC) 스프링 컨테이너에 관리한다.
@RestController
public class BlogControllerTest {

    // http://localhost:8080/test/hello
    @GetMapping("test/hello")
    public String hello(){
        return "<h1>hello, Springboot</h1>";
    }
}
