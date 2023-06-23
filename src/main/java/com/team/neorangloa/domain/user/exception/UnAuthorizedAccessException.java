package com.team.neorangloa.domain.user.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class UnAuthorizedAccessException extends BusinessException {
    public UnAuthorizedAccessException(){
        super(ErrorCode.UNAUTHORIZED_ACCESS_ERROR);
    }
}
