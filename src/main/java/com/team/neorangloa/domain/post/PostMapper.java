package com.team.neorangloa.domain.post;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.dto.PostResponse;
import com.team.neorangloa.domain.post.entity.Post;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    public Post toEntity(PostRequest postRequest) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .post_image(postRequest.getImage())
                .removed(false)
                .build();
    }

    @Builder
    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .image(post.getPost_image())
                .build();
    }
    public PostListResponse toDto(Post post){ // 목록 전체 조회 시 사용하는 DTO

        return PostListResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .build();
    }
    public List<PostListResponse> toDtoList(List<Post> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
