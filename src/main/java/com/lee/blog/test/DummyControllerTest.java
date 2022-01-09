package com.lee.blog.test;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired  // 함수가 실행될 때 자동으로 메모리에 등록된다. >> 의존성 주입 <<
    private UserRepository userRepository;


    // http://localhost:8000/blog/dummy/user/
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴받는다.(페이징 처리)
    @GetMapping("/dummy/user")
    public Page<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUsers = userRepository.findAll(pageable);

        List<User> users = pagingUsers.getContent();
        return pagingUsers;
    }

    // save 함수
    // INSERT > id를 전달하지 않거나 id에 대한 데이터가 없을 때
    // UPDATE > id를 전달하면 해당 id에 대한 데이터가 있을 때
    // email, password

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id){
        try{
            userRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id: " + id;
    }

    @Transactional  // save를 하지 않아도 update가 된다. 함수 종료시에 자동 commit이 된다.
    @PutMapping("/dummy/user/{id}")
    // @RequestBody: json 데이터를 요청 => JAVA Object(Message Converter의 Jackson 라이브러리가 변환해서 받아준다.)
    public User update(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id: " + id);
        System.out.println("pw: " + requestUser.getPassword());
        System.out.println("em: " + requestUser.getEmail() );

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패했습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
//        userRepository.save(user);

//        requestUser.setId(id);  // ID 추가
//        requestUser.setUsername("newUser");
//        userRepository.save(requestUser);
        return user;
    }



    // {id} 주소로 파라미터를 전달 받을 수 있다.
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {  // 주소와 파라미터값의 이름을 정확하게 적는다.

        // user/4를 찾으면 데이터베이스에서 못찾아오면  user가 null이 된다.
        // > 그럼 return null이 반환된다. 이 때 에러를 막기 위해서 Optional로 User객체를 감싸서 오면 객체가 null인지 아닌지 판단해서 반환한다.
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 존재하지 않습니다. id: " + id);
            }
        });

        /*
        // 람다식: 위의 식과 같다.
        User user = userRepository.findById(id).orElseThrow(()-> {
            return new IllegalArgumentException("해당 사용자는 없습니다.");
        });
        */
        /* null을 출력한다.
        User user = userRepository.findById(id).orElseGet(new Supplier<User>(){
            @Override
            public User get() {
                return new User();
            }
        });
        */

        // 요청: 웹브라우저
        // user 객체 = 자바 오브젝트
        // 웹 브라우저가 이해할 수 있는 데이터로 변환되어야 한다. -> JSON (과거에는 Gson 라이브러리를 사용했었음.)
        // 스프링 부트 = MessageConvert가 응답시에 자동으로 작동한다.
        // 자바 오브젝트를 작동하면 MessageConverter가 Jackson 라이브러리를 호출해서 user 오브젝트를 json으로 변환 후 브라우저에 전달한다.

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
