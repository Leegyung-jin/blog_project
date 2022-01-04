package com.lee.blog.service;

import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// spring이 컴포넌트 스캔을 통해서 Bean에 등록해준다. (IoC를 해준다.)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void 회원가입(User user) {
        userRepository.save(user);

    }
}
