package com.lee.blog.test;

import lombok.*;

//@Getter
//@RequiredArgsConstructor    // final을 이용한 생성자를 만들어준다.
//@Setter
//@AllArgsConstructor // 생성자 생성
@Data   // Getter+Setter
@NoArgsConstructor    // 빈 생성자
public class Member {
    // final > 불러온 데이터의 불변성을 유지하기 위해 final을 이용
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
