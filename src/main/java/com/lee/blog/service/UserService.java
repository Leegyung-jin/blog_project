package com.lee.blog.service;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        String rawPassword = user.getPassword();            // 1234 원문
        String encPassword = encoder.encode(rawPassword);   // 해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시킨 후 영속화된 User 오브젝트를 수정한다.
        // Select를 진행해서 User 오브젝트를 DB로부터 가져온다. > 영속화를 위해서 > 영속화된 오브젝트를 변경하면 자동으로 DB에 update를 시켜준다.
        User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
            return new IllegalIdentifierException("해당 회원을 찾을 수 없습니다.");
        });

        // Oauth 값에 따라 비밀번호와 이메일을 변경할 수 있도록 Validate를 체크한다.
        if(persistance.getOauth() == null || persistance.getOauth().equals("")) {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword);
            persistance.setPassword(encPassword);
            persistance.setEmail(user.getEmail());
        }

        userRepository.save(persistance);
        // 수정 함수 종료 = 서비스 종료 = 트랜잭션 종료 = 자동 commit
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이되어 변화된 것들을 자동으로 DB에 update하여 저장한다.
    }

    @Transactional(readOnly = true)
    public User 회원찾기(String username) {

        User user = userRepository.findByUsername(username).orElseGet(()->{
            return new User();
        });
        return user;
    }


    /*
    @Transactional(readOnly = true) // select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료(정합성)
    public User 로그인(User user) {
        System.out.println(userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
        return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
    */
}
