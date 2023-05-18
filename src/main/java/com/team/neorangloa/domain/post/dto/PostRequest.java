package com.team.neorangloa.domain.post.dto;

import com.team.neorangloa.domain.post.entity.Post;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@RequiredArgsConstructor
public class PostRequest {

    @NotEmpty
    @Length(max = 100, message = "제목은 최대 100글자를 넘을 수 없습니다.")
    private final String title;

    @NotEmpty
    private final String content;
    private final String image;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .post_image(this.image)
                .removed(false)
                .build();
    }
}
