package com.jesslog.service;

import com.jesslog.domain.Post;
import com.jesslog.repository.PostRepository;
import com.jesslog.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    // 생성자 주입 (@RequiredArgsConstructor)
    private final PostRepository postRepository;


    // 글을 저장하는 메서드
    public Post write(PostCreate postCreate) {
        // postCreate가 request 형태이지, 엔티티가 아니여서 들어가지지않음
        // PostCreate -> Entity
        //Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        return postRepository.save(post);

    }
}
