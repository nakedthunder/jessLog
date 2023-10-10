package com.jesslog.service;

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
    public void write(PostCreate postCreate) {
        // 레파지토리에 .save() , 근데 repo랑 entity도 없음
        // 엔티티가 아니여서 .save()로 들어가지지않음
        // 일반클래스를 엔티티 형태로 변환해줘야함,,,
        

    }
}
