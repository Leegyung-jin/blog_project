package com.lee.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> JAVA(다른 언어 포함) Object -> 테이블로 매핑해주는 기술
@Entity // User클래스가 자동으로 MySQL에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    // 빌더 패턴
//@DynamicInsert  // insert 할 때 null인 값을 쿼리에서 입력을 생략한다.
public class User {

    @Id // primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String username;    // id

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;   //

    // @ColumnDefault("user")
    // DB는 RoleType이라는게 없다.
    @Enumerated(EnumType.STRING)
    private RoleType role;    // Enum을 쓰는게 좋다. ADMIN, USER 타입이 강제가 된다.

    private String oauth;   // kakao, google, email 등 로그인 방식을 구별한다.

    @CreationTimestamp  // 시간이 자동 입력된다.
    private Timestamp createDate;



}
