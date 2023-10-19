package com.jesslog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jesslog.domain.Post;
import com.jesslog.repository.PostRepository;
import com.jesslog.request.PostCreate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired // 필드주입으로 받는다.
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("/post 요청 시 Hello world 출력")
    void postTest() throws Exception {
        // PostCreate 클래스에서 생성자를 만든것을 담아준다.
        // 나중에 다른 개발자가 생성자 필드를 바꾼다면? ... 나중에 버그를 발견하기 어려워짐
        // 원인 찾기가 어려워져서,
        //PostCreate request = new PostCreate("제목입니다.", "내용입니다.");
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // 잭슨 프로세싱해주는 라이브러리
       // ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request); // getter가 자바빈규약룰을따라서 json형태로 가공을 해준다.

        // 생성자로 통해 넣은 값들이 ObjectMapper를 통해 String으로 잘 들어갔는지 확인하기위해
        System.out.println(json);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""))
                .andDo(print()); // 이거는 http 요청에 대한 서머리를 남겨준다. 테스트에 대한 서머리를 확인할 수 있다.
    }

    @Test
    @DisplayName("/post 요청 시 title 빈값이면 안됨")
    void titleNotNullTest() throws Exception {

        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/post 요청 시 DB에 값이 저장된다.")
    void insertToDbByPost() throws Exception {

        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        // 잭슨 프로세싱해주는 라이브러리
        // ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(request); // getter가 자바빈규약룰을따라서 json형태로 가공을 해준다.

        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());

        // then, 요청한 json이 DB에 저장되기 때문에
        // Assertions.assertEquals()
        assertEquals(1L, postRepository.count());

    }

    @Test
    @DisplayName("글 1개 조회")
    void test4() throws Exception {
        // given
        Post post = Post.builder()
                .title("12345123451234")
                .content("bar")
                .build();

        postRepository.save(post);

        //expected
        mockMvc.perform(MockMvcRequestBuilders.get("/post/{postId}", post.getId())
                        .contentType(APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value("1234512345"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }

}
