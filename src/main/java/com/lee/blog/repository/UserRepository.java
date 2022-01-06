package com.lee.blog.repository;

import com.lee.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


// DAO
// 자동으로 bean 등록이 된다. > @Repository 어노테이션을 생략할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = ? ;
    Optional<User> findByUsername(String username);



    // JPA Naming 쿼리 전략
    // 실존하지 않지만 아래의 쿼리를 실행 - 전통적인 로그인 방식
    // SELECT * FROM user WHERE username = ? AND password = ?
    // User findByUsernameAndPassword(String username, String password);

    //    간단한 로그인 방식 - 위와 아래의 결과는 같다.
    //    @Query(value = "SELECT * FROM user WHERE username =? AND password =?", nativeQuery = true)
    //    User login(String username, String password);
}
