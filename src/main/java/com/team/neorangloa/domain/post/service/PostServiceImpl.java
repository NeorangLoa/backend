package com.team.neorangloa.domain.post.service;

import com.team.neorangloa.domain.post.PostMapper;
import com.team.neorangloa.domain.post.dto.PostListResponse;
import com.team.neorangloa.domain.post.dto.PostRequest;
import com.team.neorangloa.domain.post.entity.Post;
import com.team.neorangloa.domain.post.entity.PostRecommendation;
import com.team.neorangloa.domain.post.exception.PostAuthorMismatchException;
import com.team.neorangloa.domain.post.repository.PostRecommendationRepository;
import com.team.neorangloa.domain.post.repository.PostRepository;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostRecommendationRepository postRecommendationRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional
    public void createNewPost(PostRequest postRequest, User loginUser) {
        Post post = postMapper.toEntity(postRequest, loginUser);
        postRepository.save(post);
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND_ERROR));
    }



    // 조회수 중복 방지 함수
    @Override
    public void updateViewCounts(Long postId, HttpServletRequest request, HttpServletResponse response) {
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + postId.toString() + "]")) {
                increaseViewCounts(postId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + postId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(oldCookie);
            }
        } else {
            increaseViewCounts(postId);
            Cookie newCookie = new Cookie("postView", "[" + postId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            //newCookie.setDomain(".우리가 사용하는 도메인 주소"); // 우리가 사용하는 도메인 주소 예시: ".tistory.com"
            response.addCookie(newCookie);
        }
    }

    // 조회수 증가 함수
    @Override
    @Transactional
    public void increaseViewCounts(Long postId) {
        postRepository.updateViewCounts(postId);
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
    public void updatePost(User loginUser, Post post, PostRequest postRequest) {
        checkIsAuthor(loginUser,post);
        post.updatePost(postRequest);
    }

    @Transactional
    @Override
    public void deletePost(User loginUser, Post post) {
        checkIsAuthor(loginUser,post);
        post.setRemoved(true);
        postRepository.save(post);
    }

    @Transactional
    @Override
    public void checkIsAuthor(User loginUser, Post post) {
        if (!loginUser.getId().equals(post.getAuthor().getId())) {
            throw new PostAuthorMismatchException();
        }
    }

    @Transactional
    @Override
    public int updatePostRecommendation(User loginUser, Post post) {

        Optional<PostRecommendation> postRecommendation = postRecommendationRepository.findByClientAndPost(loginUser, post);

        if (postRecommendation.isEmpty()){
            PostRecommendation newPostRecommendation = PostRecommendation.builder()
                    .post(post)
                    .user(loginUser)
                    .isRecommended(true)
                    .build();
            postRecommendationRepository.save(newPostRecommendation);
            post.increaseRecommendationCount();
        } else {
            postRecommendationRepository.deleteByUserAndPost(loginUser, post);
            postRecommendation.get().setRecommended(false);
            post.decreaseRecommendationCount();
        }

        return post.getRecommendationCount();
    }
}
