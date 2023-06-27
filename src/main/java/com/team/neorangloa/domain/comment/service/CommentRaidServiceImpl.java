package com.team.neorangloa.domain.comment.service;

import com.team.neorangloa.domain.comment.CommentRaidMapper;
import com.team.neorangloa.domain.comment.dto.CommentRaidRequest;
import com.team.neorangloa.domain.comment.dto.CommentRaidResponse;
import com.team.neorangloa.domain.comment.dto.CommentRaidUpdateRequest;
import com.team.neorangloa.domain.comment.entity.CommentRaid;
import com.team.neorangloa.domain.comment.exception.CommentAuthorMismatchException;
import com.team.neorangloa.domain.comment.repository.CommentRaidRepository;
import com.team.neorangloa.domain.post.entity.PostRaid;
import com.team.neorangloa.domain.post.exception.PostAuthorMismatchException;
import com.team.neorangloa.domain.post.service.PostRaidService;
import com.team.neorangloa.domain.user.entity.User;
import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentRaidServiceImpl implements CommentRaidService{
    private final CommentRaidRepository commentRaidRepository;
    private final CommentRaidMapper commentRaidMapper;
    private final PostRaidService postRaidService;

    @Override
    @Transactional
    public void createCommentRaid(CommentRaidRequest commentRaidRequest, User loginUser) {
        PostRaid postRaid = postRaidService.findPostRaidById(commentRaidRequest.getPostRaidId());
        CommentRaid commentRaid = commentRaidMapper.toEntity(commentRaidRequest, postRaid, loginUser);
        commentRaidRepository.save(commentRaid);
    }

    @Override
    public CommentRaid findCommentRaidById(Long commentRaidId) {
        return commentRaidRepository.findCommentRaidById(commentRaidId).orElseThrow(
                () -> new BusinessException(ErrorCode.COMMENT_NOT_FOUND_ERROR));
    }

    @Override
    @Transactional
    public void updateCommentRaid(User loginUser, CommentRaid commentRaid, CommentRaidUpdateRequest request) {
        checkIsAuthor(loginUser, commentRaid);
        commentRaid.updateCommentRaid(request);
    }

    @Override
    @Transactional
    public void deleteCommentRaid(User loginUser, CommentRaid commentRaid) {
        checkIsAuthor(loginUser, commentRaid);
        commentRaid.setRemoved(true);
        commentRaidRepository.save(commentRaid);
    }

    @Override
    @Transactional
    public List<CommentRaidResponse> findCommentRaidByPostRaidId(Integer page, Integer size, Long postRaidId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<CommentRaid> commentRaids = commentRaidRepository.findCommentRaidByPostRaidId(pageRequest, postRaidId);
        return commentRaidMapper.toDtoList(commentRaids);
    }

    @Override
    @Transactional
    public void checkIsAuthor(User loginUser, CommentRaid commentRaid){
        if (!loginUser.getId().equals(commentRaid.getAuthor().getId())) {
            throw new CommentAuthorMismatchException();
        }
    }
}
