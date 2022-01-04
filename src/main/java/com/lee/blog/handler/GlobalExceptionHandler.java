package com.lee.blog.handler;

import com.lee.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice   // 어느곳에서도 참조할 수 있다.
@RestController
public class GlobalExceptionHandler {
    /*
    IllegalArgumentException의 경우 출력
    @ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e){
        return "<h1>" + e.getMessage() + "</h1>";
    }
    */

    // 모든 Exception에서 출력
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(IllegalArgumentException e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
