package com.lee.blog.repository;

import com.lee.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


// DAO
// 자동으로 bean 등록이 된다. > @Repository 어노테이션을 생략할 수 있다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
