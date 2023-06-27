package com.team.neorangloa.domain.post.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class PostAuthorMismatchException extends BusinessException {
    public PostAuthorMismatchException() {
        super(ErrorCode.POST_MISMATCH_AUTHOR_EROOR);
    }
}
