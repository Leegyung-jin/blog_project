package com.lee.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 사용을 위해
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob    // 대용량 데이터를 사용할 때 씀
    private String content; // 섬머노트 라이브러리 사용 예정 <HTML> 태그가 섞여서 디자인된다.

//    @ColumnDefault("0")
    private int count;  // 조회수

    @ManyToOne(fetch = FetchType.EAGER)  // Many = Board, User = One > 한 명의 유저는 여러 개의 게시글을 작성할 수 있다. 기본 패치 전략->EAGER
    @JoinColumn(name="userId")  // Foreign Key 실제로 데이터가 만들어질 때에는 userId로 만들어진다.
    private User user;  // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER)  // mappedBy: 연관관계의 주인이 아니다(FK가 아님 > DB에 컬럼을 만들지 않는다.) 기본 패치 전략->EAGER
    private List<Reply> reply;

    @CreationTimestamp  // 데이터가 insert, update 될 때 값이 전송된다.
    private Timestamp createDate;

}
