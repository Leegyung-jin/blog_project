package com.lee.blog.config.auth;

import com.lee.blog.model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고
// 완료가 되면 UserDetails 타입의 오브젝트를 스프링 시큐리티의 고유 세션 저장소에 저장한다.
@Data
public class PrincipalDetail implements UserDetails {    // implements로 타입을 바꿈
    private User user;  // composition: 객체를 품고 있음

    public PrincipalDetail(User user){
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 확인 후 리턴한다.(true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 확인 후 리턴한다. (ture: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료여부를 리턴한다. (true: 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부를 리턴한다.(true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정의 권한 목록을 리턴한다.(권한이 여러개 있을 수 있는데 우리는 하나만 출력함)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> colletors = new ArrayList<>();

        /*
        colletors.add(new GrantedAuthority() {
            // 익명클래스가 만들어지고 추상 메소드가 오버라이딩되며 리턴 값을 String으로 준다.
            @Override
            public String getAuthority() {
                // "ROLE_"은 스프링 명명 규칙임. 꼭 붙여줘야한다.
                return "ROLE_"+user.getRole();  // ROLE_USER
            }
        });
        */

        // 위와 같이 자바 안에 함수를 넣을 수 없다. 따라서 아래와 같이 작성할 수 있다.
        colletors.add(()->{ return "ROLE_" + user.getRole(); });

        return colletors;
    }
}
