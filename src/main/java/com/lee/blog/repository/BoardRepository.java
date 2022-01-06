package com.lee.blog.repository;

import com.lee.blog.model.Board;
import com.lee.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Integer> {

}
