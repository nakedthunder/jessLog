package com.jesslog.response;

import com.jesslog.domain.Post;
import lombok.Builder;
import lombok.Getter;
/**
*  서비스 정책에 맞는 클래스 생성
* */
@Getter
public class PostResponse {
    private final Long id;
    private final String title;
    private final String content;

    // constructor overloading
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }


    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}
