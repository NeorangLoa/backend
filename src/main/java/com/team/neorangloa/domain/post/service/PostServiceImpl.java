package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.PostMapper;
import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.repository.PostRepository;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Transactional
    public void createNewPost(PostRequest postRequest) {
        Post post = postMapper.toEntity(postRequest);
        postRepository.save(post);
    }
    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND_ERROR));
    }

    @Transactional
    @Override
    public List<PostListResponse> getPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<Post> posts = postRepository.findAllPostByCreatedAtDesc(pageRequest).getContent();

        return postMapper.toDtoList(posts);
    }

    @Transactional
    @Override
    public void updatePost(Post post, PostRequest postRequest) {
        post.updatePost(postRequest);
    }

    @Transactional
    @Override
    public void deletePost(Post post) {
        post.setRemoved(true);
        postRepository.save(post);
    }
}
