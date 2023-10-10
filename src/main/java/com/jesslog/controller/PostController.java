package com.jesslog.controller;

import com.jesslog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController // Controller 도 할 수 있는데 데이터기반으로 응답을
public class PostController {

    /* [Spring 데이터 검증]
    * - @Valid는 @NotBalnk 를 통해 empty, null 검증
    * - BindingResult / .hasErrors()
    * */
    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate request) throws Exception {

        // 에러가 있는 경우
       /* if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // 에러난 곳
            String errorMessage = firstFieldError.getDefaultMessage(); // 에러메세지

            // return 자료형 Map타입 post()메서드도 return이 Map 자료형
            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }*/
        // 디비를 직접 여기서 저장하지말고 service레이어에서 repo를 호출하는 방법으로 ..

        return Map.of();
    }
}
