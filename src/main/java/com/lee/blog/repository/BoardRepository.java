package com.lee.blog.repository;

import com.lee.blog.model.Board;
import com.lee.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Integer> {
    // 아무 함수도 없지만 JPARepository가 모든 것을 들고 있다.
}
