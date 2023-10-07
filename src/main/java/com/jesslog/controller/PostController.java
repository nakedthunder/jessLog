package com.jesslog.controller;

import com.jesslog.request.PostCreate;
import lombok.Value;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController // Controller 도 할 수 있는데 데이터기반으로 응답을
public class PostController {

    @PostMapping("/posts")
    public Map<String, String> post(@RequestBody @Valid PostCreate paramas, BindingResult result) throws Exception {
        log.info("postCreate: ", paramas.toString());

        // 에러가 있는 경우
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); // 에러난 곳
            String errorMessage = firstFieldError.getDefaultMessage(); // 에러메세지

            // Map 에 넣기
            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error; //  return 타입 맞쳐주기
        }

        return Map.of();
    }
}
