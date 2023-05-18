package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.repository.PostRepository;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Transactional
    public void createNewPost(PostRequest postRequest) {
        Post post = postRequest.toEntity();
        postRepository.save(post);
    }
    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public void updatePost(Post post, PostRequest postRequest) {
        post.updatePost(postRequest);
    }
}
