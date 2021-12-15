package me.hyunsoo.product.user.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Date;
import java.time.Instant;

//ERROR 처리

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    //전체적인 서버 에러 - 500X
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().
                timeStamp(Date.from(Instant.now()))
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponse);
    }

    //찾지 못했을 때
    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity handleNotFoundException(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = ExceptionResponse.builder().
                timeStamp(Date.from(Instant.now()))
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}