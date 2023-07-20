package com.team.neorangloa.domain.post;

import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.dto.PostResponse;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.user.entity.User;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostMapper {

    public Post toEntity(PostRequest postRequest, User loginUser) {
        return Post.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .removed(false)
                .author(loginUser)
                .build();
    }

    @Builder
    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .nickname(post.getAuthor().getNickname())
                .content(post.getContent())
                .viewCounts(post.getViewCounts())
                .recommendationCount(post.getRecommendationCount())
                .createdAt(post.getCreatedTime())
                .build();
    }
    public PostListResponse toDto(Post post){ // 목록 전체 조회 시 사용하는 DTO

        return PostListResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .nickname(post.getAuthor().getNickname())
                .viewCounts(post.getViewCounts())
                .recommendationCount(post.getRecommendationCount())
                .createdAt(post.getCreatedTime())
                .build();
    }
    public List<PostListResponse> toDtoList(List<Post> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
