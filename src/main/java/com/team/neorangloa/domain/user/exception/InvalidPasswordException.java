package com.team.neorangloa.domain.user.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException(){
        super(ErrorCode.INVALID_PASSWORD);
    }
}
