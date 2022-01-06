package com.lee.blog.service;

import com.lee.blog.model.Board;
import com.lee.blog.model.User;
import com.lee.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기(Board board, User user) {  // title, content만 받는다.
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
}
