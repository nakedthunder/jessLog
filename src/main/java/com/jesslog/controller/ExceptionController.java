package com.jesslog.controller;

import com.jesslog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody // 이거를 통해서 hadMap을 json형태로 응답을 할 수 있다.
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e) {
//        FieldError fieldError = e.getFieldError();
//        String field = fieldError.getField();
//        String message = fieldError.getDefaultMessage();

//        Map<String, String> response = new HashMap<>();
//        response.put(field, message);
        //return response;

        ErrorResponse response = new ErrorResponse("400", "잘못된 요청입니다.");

        // ArrayList 안에 필드객체가 담겨져 있는 것
        for (FieldError fieldError : e.getFieldErrors()) {
            // addValidation 메서드 > ErrorResponse 클래스안에 정생
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }
}
