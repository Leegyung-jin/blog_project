package com.lee.blog.controller.api;

import com.lee.blog.config.auth.PrincipalDetail;
import com.lee.blog.dto.ResponseDto;
import com.lee.blog.model.Board;
import com.lee.blog.model.User;
import com.lee.blog.service.BoardService;
import com.lee.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
