package com.jesslog.controller;

import com.jesslog.domain.Post;
import com.jesslog.request.PostCreate;
import com.jesslog.response.PostResponse;
import com.jesslog.service.PostService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController // Controller 도 할 수 있는데 데이터기반으로 응답을
@RequiredArgsConstructor
public class PostController {

    // 0911 확인하기
    private final PostService postService;

    /* [Spring 데이터 검증]
    * - @Valid는 @NotBalnk 를 통해 empty, null 검증
    * - BindingResult / .hasErrors()
    * */
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) throws Exception {

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

         postService.write(request);

        // 현재 빈 객체가 내려가는데, post요청이면 따로 응답을 주지않는다.
        // 그래서 메서드타입을 void로 변환
        //return Map.of();
    }

    /**
     * /post/{postId} -> 글 단건 조회
     */
     @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long postId) {
        return postService.get(postId);
     }

     @GetMapping("/posts")
    public List<PostResponse> getList() {
         return postService.getList(1);
     }
}
