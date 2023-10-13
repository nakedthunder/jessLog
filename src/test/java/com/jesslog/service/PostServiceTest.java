package com.jesslog.service;

import com.jesslog.repository.PostRepository;
import com.jesslog.request.PostCreate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    // mockRepo를 넣어줘도 되나, 실제 레파지토리를 주입받아오되는데, 그냥 PostService 자체를 주입받자
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        // postService가 주입이 안되서 @SpringBootTest 를 추가함
        postService.write(postCreate);

        //then
        Assertions.assertEquals(1L, postRepository.count());
    }


}