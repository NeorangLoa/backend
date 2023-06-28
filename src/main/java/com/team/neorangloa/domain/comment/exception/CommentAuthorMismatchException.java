package com.team.neorangloa.domain.comment.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class CommentAuthorMismatchException extends BusinessException {
    public CommentAuthorMismatchException() {
        super(ErrorCode.COMMENT_MISMATCH_AUTHOR_ERROR);
    }
}
