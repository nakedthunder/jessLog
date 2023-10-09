package com.jesslog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    // final 로 지정해서 @RequiredArgsConstructor 를 생성해준다.
    private final String code;
    private final String message;
    private Map<String, String> validation = new HashMap<>(); // final로 하면 생성자에서 오류발생, 초기값이 null이여서 new HashMap

    public void addValidation(String fieldName, String errorMessage) {
        this.validation.put(fieldName, errorMessage);
    }

}
