package com.lee.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> JAVA(다른 언어 포함) Object -> 테이블로 매핑해주는 기술
@Entity // User클래스가 자동으로 MySQL에 테이블이 생성된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder    // 빌더 패턴
public class User {

    @Id // primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username;    // id

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;   //

    @ColumnDefault("'user'")
    private String role;    // Enum을 쓰는게 좋다. admin, user, manager

    @CreationTimestamp  // 시간이 자동 입력된다.
    private Timestamp createDate;



}
