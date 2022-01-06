package com.lee.blog.config;

import com.lee.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Bean 등록: Spring container에서 객체를 관리할 수 있게 하는 것
@Configuration          // Bean등록 (IoC관리)
@EnableWebSecurity      // Security 필터 추가 > 활성화된 Spring Security가 적용된 파일에서 설정을 진행하겠다는 의미.
@EnableGlobalMethodSecurity(prePostEnabled = true)  // 특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다는 의미
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean   // 함수가 리턴하는 값을 스프링이 관리하며 IoC가 된다.
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }

    // Security가 대신 로그인할 때 password를 가로채기한다.
    // 해당 패스워드가 어떤 해시가 되어 회원 가입이 되었는지 확인할 수 있어야 같은 해쉬로 암호화하여 DB의 해시와 비교할 수 있다.
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());   // 이걸 넣지 않으면 패스워드 비교할 수 없음
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()   // csrf 토큰 비활성화 (테스트 시 걸어두는게 좋다.)
        .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")    // /auth로 들어오는건 다 들어올 수 있다.
                .permitAll()
                .anyRequest()
                .authenticated()
        .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")   // 스프링 시큐리티가 이 주소로 요청한 로그인을 가로챈다. > 대신 로그인을 처리한다. principalDetailService로 이동
                .defaultSuccessUrl("/");    // 로그인 성공 시 url로 이동

    }
}
