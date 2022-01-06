package com.lee.blog.controller.api;

import com.lee.blog.dto.ResponseDto;
import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){
        System.out.println("UserApiController: save 호출");

        // 실제로 DB에 insert를 하고 아래에서 return을 하면 된다.
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  // 자바 오브젝트를 JSON으로 변환해서 리턴한다.
    }

    // 로그인 작성하지 않는 이유:  Security Config에서 가로챌 것



    /*
     전통적인 기본 로그인 개념
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController: login 호출");
        User principal = userService.로그인(user); // principal (접근 주체)


        if(principal != null) {
            session.setAttribute("principal", principal);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  // 자바 오브젝트를 JSON으로 변환해서 리턴한다.
    }
    */
}
