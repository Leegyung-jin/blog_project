package com.lee.blog.test;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Supplier;

@RestController
public class DommyControllerTest {

    @Autowired  // 함수가 실행될 때 자동으로 메모리에 등록된다. >> 의존성 주입 <<
    private UserRepository userRepository;

    // {id} 주소로 파라미터를 전달 받을 수 있다.
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {  // 주소와 파라미터값의 이름을 정확하게 적는다.

        // user/4를 찾으면 데이터베이스에서 못찾아오면  user가 null이 된다.
        // > 그럼 return null이 반환된다. 이 때 에러를 막기 위해서 Optional로 User객체를 감싸서 오면 객체가 null인지 아닌지 판단해서 반환한다.
        User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User();  // 빈 객체를 return한다.
            }
        });
        return user;

    }

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 Body에 username, password, email 데이터를 가지고 요청하게 되면 아래에 들어간다.
    @PostMapping("/dummy/join")
//    public String join(String username, String password, String email){ // key=value (약속된 규칙) 형식으로 등록해준다.
//
//
    public String join(User user){
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);    // 회원가입 시 기본 값을 설정한다.
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";



    }

}
