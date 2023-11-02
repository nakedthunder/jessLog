package com.jesslog.service;

import com.jesslog.domain.Post;
import com.jesslog.repository.PostRepository;
import com.jesslog.request.PostCreate;
import com.jesslog.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

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

    @Test
    @DisplayName("단건 조회")
    void test2() {
        Post requestPost = Post.builder()
                .title("foo")
                .content("bar")
                .build();
        // 저장시켜주고
        postRepository.save(requestPost);

        // get으로 다시 조회해오기
        PostResponse response = postService.get(requestPost.getId());

        // 테스트 확인
        Assertions.assertNotNull(response);
        // 테스트 검증
        Assertions.assertEquals("foo", response.getTitle());
        Assertions.assertEquals("bar", response.getContent());
    }

    @Test
    @DisplayName("리스트 조회")
    void test3() {
        // given: 게시글 30개 저장하기
        List<Post> requestPosts = IntStream.range(0,30)
                .mapToObj(i -> Post.builder()
                        .title("jess log name - " + i)
                        .content("제주도 애월 맛집 - " + i)
                        .build())
                        .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        // when
        List<PostResponse> posts = postService.getList(1);

        // then
        assertEquals(30, posts.size());
    }

}