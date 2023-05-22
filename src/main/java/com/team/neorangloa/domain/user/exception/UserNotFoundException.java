package com.team.neorangloa.domain.user.exception;

import com.team.neorangloa.global.error.ErrorCode;
import com.team.neorangloa.global.error.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND_ERROR);
    }
}
