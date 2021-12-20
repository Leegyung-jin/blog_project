package com.lee.blog.test;

import org.springframework.web.bind.annotation.*;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest";

    @GetMapping("/http/lombok")
    public String lombokTest(){
//        Member m  new Member(1, "ssar", "1234", "email");
        Member m = Member.builder().username("sarr").password("1234").email("ssar@aa.com").build();

        System.out.println(TAG + " getter: " + m.getUsername());
        m.setUsername("Lee");
        System.out.println(TAG + " setter: " + m.getUsername());

        return "lombok Test 완료";
    }

    // 인터넷 브라우저의 요청은 get밖에 할 수 없다. (주소창에 넣어서 하는 것)
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
//    public String getTest(@RequestParam int id, @RequestParam String username){
//        return "get 요청: " + id + ", " + username;
//    }
    public String getTest(Member m){    // id=1&username=ssar&password=1234&emai=ssar@nate.com와 같은 쿼리 스트링을 Member에 넣어줄 수 있다.
        return "get 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/post  (create)
    @PostMapping("/http/post")  // text/plain, application/json
    public String postTest(@RequestBody Member m) {  // MessageConverter(스프링부트)
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/put   (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m){
        return "put 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    // http://localhost:8080/http/delete    (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest(){
        return "delete 요청";
    }

}
