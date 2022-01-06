package com.lee.blog.service;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


// spring이 컴포넌트 스캔을 통해서 Bean에 등록해준다. (IoC를 해준다.)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void 회원가입(User user) {
        String rawPassword = user.getPassword();    // 1234 원문
        String encPassword = encoder.encode(rawPassword);   // 해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }


    /*
    @Transactional(readOnly = true) // select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
    public User 로그인(User user) {
        System.out.println(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
    */
}
