package com.jesslog.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Setter
@Getter
public class PostCreate {
    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;
    @NotBlank(message = "컨텐츠를 입력해주세요.")
    private String content;

    // 생성자 생성 , @AllArgucontr...
    @Builder // java 디자인패턴, 빌더패턴
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
