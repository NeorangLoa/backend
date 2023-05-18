package com.team.neorangloa.domain.post.dto;

import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@RequiredArgsConstructor
public class PostResponse {

    private final Long postId;

    private final String nickname;
    private final String title;

    private final String content;
    private final String image;

    @Builder
    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .nickname(post.getAuthor().getNickname())
                .title(post.getTitle())
                .content(post.getContent())
                .image(post.getPost_image())
                .build();
    }
}
