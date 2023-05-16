package com.team.neorangloa.global.error.exception;

import com.team.neorangloa.global.error.ErrorCode;

public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
